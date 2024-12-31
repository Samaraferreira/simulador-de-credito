package com.simulator.mock;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.builder.SimulationBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SimulationMock {


    public static SimulationDomain getMockedSimulation() {
        return new SimulationBuilder()
                .amount(BigDecimal.valueOf(10000))
                .birthDate(LocalDate.now().minusYears(24))
                .paymentTerm(12)
                .email("test@gmail.com")
                .annualInterestRate(0.05)
                .monthlyPayment(BigDecimal.valueOf(856.07))
                .totalInterestAmount(BigDecimal.valueOf(272.90))
                .totalAmount(BigDecimal.valueOf(10272.90))
                .build();
    }

    public static SimulationDomain createSimulation(int age, double annualInterestRate, BigDecimal monthlyPayment,
                                                     BigDecimal totalInterestAmount, BigDecimal totalAmount) {
        return new SimulationBuilder()
                .amount(BigDecimal.valueOf(10000))
                .birthDate(LocalDate.now().minusYears(age))
                .paymentTerm(12)
                .email("test@gmail.com")
                .annualInterestRate(annualInterestRate)
                .monthlyPayment(monthlyPayment)
                .totalInterestAmount(totalInterestAmount)
                .totalAmount(totalAmount)
                .build();
    }
}
