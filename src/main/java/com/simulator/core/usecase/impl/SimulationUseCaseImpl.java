package com.simulator.core.usecase.impl;


import com.simulator.core.domain.Simulation;
import com.simulator.core.gateway.InterestRateGateway;
import com.simulator.core.gateway.NotificationGateway;
import com.simulator.core.gateway.SimulationGateway;
import com.simulator.core.usecase.SimulationUseCase;

import java.math.MathContext;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimulationUseCaseImpl implements SimulationUseCase {

    private static final int SCALE = 2;
    private static final int MONTHS_IN_YEAR = 12;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final NotificationGateway notificationGateway;
    private final SimulationGateway simulationGateway;
    private final InterestRateGateway interestRateGateway;

    public SimulationUseCaseImpl(NotificationGateway notificationGateway,
                                 SimulationGateway simulationGateway,
                                 InterestRateGateway interestRateGateway) {
        this.notificationGateway = notificationGateway;
        this.simulationGateway = simulationGateway;
        this.interestRateGateway = interestRateGateway;
    }

    @Override
    public Simulation create(Simulation simulation) {

        double annualRate = interestRateGateway.getAnnualInterestRate(simulation.getAge());

        double monthlyRate = annualRate / MONTHS_IN_YEAR;

        BigDecimal monthlyPayment = calculateMonthlyPayment(simulation.getAmount(), monthlyRate, simulation.getPaymentTerm());

        BigDecimal totalAmount = calculateTotalAmount(simulation, monthlyPayment);

        BigDecimal totalInterestAmount = calculateTotalInterestAmount(simulation, totalAmount);

        simulation.setMonthlyPayment(round(monthlyPayment));
        simulation.setTotalAmount(totalAmount);
        simulation.setTotalInterestAmount(totalInterestAmount);

        Simulation savedSimulation = simulationGateway.create(simulation);
        notificationGateway.notify(savedSimulation);

        return simulation;
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, double monthlyRate, int paymentTerm) {

        double denominator = (1 - Math.pow((1 + monthlyRate), -paymentTerm));

        return amount.multiply(BigDecimal.valueOf(monthlyRate))
                .divide(BigDecimal.valueOf(denominator), MathContext.DECIMAL128);
    }

    private BigDecimal calculateTotalAmount(Simulation simulation, BigDecimal monthlyInstallmentAmount) {
        BigDecimal term = BigDecimal.valueOf(simulation.getPaymentTerm());
        return round(monthlyInstallmentAmount.multiply(term));
    }

    private BigDecimal calculateTotalInterestAmount(Simulation simulation, BigDecimal totalAmount) {
        return round(totalAmount.subtract(simulation.getAmount()));
    }

    private BigDecimal round(BigDecimal amount) {
        return amount.setScale(SCALE, ROUNDING_MODE);
    }
}
