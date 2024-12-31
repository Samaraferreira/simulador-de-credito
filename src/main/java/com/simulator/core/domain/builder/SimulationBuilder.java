package com.simulator.core.domain.builder;

import com.simulator.core.domain.SimulationDomain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class SimulationBuilder {

    private UUID id;
    private BigDecimal amount;
    private LocalDate birthDate;
    private int paymentTerm;
    private double annualInterestRate;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal monthlyPayment;
    private BigDecimal totalInterestAmount;
    private String email;

    public SimulationBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public SimulationBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public SimulationBuilder birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public SimulationBuilder paymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
        return this;
    }

    public SimulationBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SimulationBuilder totalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public SimulationBuilder annualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
        return this;
    }

    public SimulationBuilder monthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
        return this;
    }

    public SimulationBuilder totalInterestAmount(BigDecimal totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
        return this;
    }

    public SimulationBuilder email(String email) {
        this.email = email;
        return this;
    }

    public SimulationDomain build() {
        return new SimulationDomain(id, amount, birthDate, paymentTerm, annualInterestRate, createdAt, totalAmount, monthlyPayment, totalInterestAmount, email);
    }
}