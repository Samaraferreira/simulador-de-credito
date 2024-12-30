package com.simulator.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public class Simulation {

    private UUID id;
    private BigDecimal amount;
    private LocalDate birthDate;
    private int paymentTerm;
    private double annualInterestRate;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private BigDecimal monthlyInstallmentAmount;
    private BigDecimal totalInterestAmount;
    private String email;

    public Simulation(UUID id, BigDecimal amount, LocalDate birthDate, int paymentTerm, LocalDateTime createdAt) {
        this.id = id;
        this.amount = amount;
        this.birthDate = birthDate;
        this.paymentTerm = paymentTerm;
        this.createdAt = createdAt;
    }

    public Simulation(BigDecimal amount, LocalDate birthDate, int paymentTerm, String email ) {
        this.email = email;
        this.paymentTerm = paymentTerm;
        this.birthDate = birthDate;
        this.amount = amount;
    }

    public Simulation(UUID id, BigDecimal totalAmount, BigDecimal monthlyInstallmentAmount,
                      BigDecimal totalInterestAmount) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
        this.totalInterestAmount = totalInterestAmount;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
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

    public BigDecimal getMonthlyInstallmentAmount() {
        return monthlyInstallmentAmount;
    }

    public BigDecimal getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setMonthlyPayment(BigDecimal monthlyInstallmentAmount) {
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
    }

    public void setTotalInterestAmount(BigDecimal totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
