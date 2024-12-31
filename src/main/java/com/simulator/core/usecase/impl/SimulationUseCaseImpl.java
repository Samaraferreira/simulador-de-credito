package com.simulator.core.usecase.impl;


import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationBuilder;
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
    public SimulationDomain create(SimulationRequestDomain simulationRequestDomain) {

        double annualRate = interestRateGateway.getAnnualInterestRate(simulationRequestDomain.getAge());

        double monthlyRate = annualRate / MONTHS_IN_YEAR;

        BigDecimal monthlyPayment = calculateMonthlyPayment(simulationRequestDomain.getAmount(), monthlyRate,
                simulationRequestDomain.getPaymentTerm());

        BigDecimal totalAmount = calculateTotalAmount(BigDecimal.valueOf(simulationRequestDomain.getPaymentTerm()),
                monthlyPayment);

        BigDecimal totalInterestAmount = calculateTotalInterestAmount(simulationRequestDomain.getAmount(), totalAmount);

        SimulationDomain simulationDomain = buildSimulationDomain(simulationRequestDomain, monthlyPayment, annualRate,
                totalAmount, totalInterestAmount);

        simulationGateway.create(simulationDomain);
        notificationGateway.notify(simulationDomain);

        return simulationDomain;
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, double monthlyRate, int paymentTerm) {

        double denominator = (1 - Math.pow((1 + monthlyRate), -paymentTerm));

        return amount.multiply(BigDecimal.valueOf(monthlyRate))
                .divide(BigDecimal.valueOf(denominator), MathContext.DECIMAL128);
    }

    private BigDecimal calculateTotalAmount(BigDecimal paymentTerm, BigDecimal monthlyPayment) {
        return round(monthlyPayment.multiply(paymentTerm));
    }

    private BigDecimal calculateTotalInterestAmount(BigDecimal amount, BigDecimal totalAmount) {
        return round(totalAmount.subtract(amount));
    }

    private SimulationDomain buildSimulationDomain(SimulationRequestDomain simulationRequestDomain,
                                                   BigDecimal monthlyPayment, double annualRate, BigDecimal totalAmount,
                                                   BigDecimal totalInterestAmount) {
        return new SimulationBuilder()
                .amount(simulationRequestDomain.getAmount())
                .email(simulationRequestDomain.getEmail())
                .paymentTerm(simulationRequestDomain.getPaymentTerm())
                .birthDate(simulationRequestDomain.getBirthDate())
                .monthlyPayment(monthlyPayment)
                .annualInterestRate(annualRate)
                .monthlyPayment(round(monthlyPayment))
                .totalAmount(totalAmount)
                .totalInterestAmount(totalInterestAmount)
                .build();
    }

    private BigDecimal round(BigDecimal amount) {
        return amount.setScale(SCALE, ROUNDING_MODE);
    }
}
