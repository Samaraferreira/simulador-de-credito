package com.simulator.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class SimulationDomain {

    private UUID id;
    private BigDecimal amount;
    private LocalDate birthDate;
    private int paymentTerm;
    private double annualInterestRate;
    private BigDecimal totalAmount;
    private BigDecimal monthlyPayment;
    private BigDecimal totalInterestAmount;
    private String email;
    private LocalDateTime createdAt;

    public SimulationDomain(UUID id, BigDecimal amount, LocalDate birthDate, int paymentTerm, double annualInterestRate, LocalDateTime createdAt, BigDecimal totalAmount, BigDecimal monthlyPayment, BigDecimal totalInterestAmount, String email) {
        this.id = id;
        this.amount = amount;
        this.birthDate = birthDate;
        this.paymentTerm = paymentTerm;
        this.annualInterestRate = annualInterestRate;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.monthlyPayment = monthlyPayment;
        this.totalInterestAmount = totalInterestAmount;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public BigDecimal getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setTotalInterestAmount(BigDecimal totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }
}
