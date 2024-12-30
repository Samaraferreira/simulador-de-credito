package com.simulator.infrastructure.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.simulator.core.domain.Simulation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName = "simulation")
public class SimulationEntity {

    @DynamoDBHashKey
    private String id;
    @DynamoDBAttribute
    private BigDecimal amount;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate birthDate;
    @DynamoDBAttribute
    private int paymentTerm;
    @DynamoDBAttribute
    private double annualInterestRate;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BigDecimalConverter.class)
    private BigDecimal totalAmount;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BigDecimalConverter.class)
    private BigDecimal monthlyInstallmentAmount;
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BigDecimalConverter.class)
    private BigDecimal totalInterestAmount;
    @DynamoDBAttribute
    private String email;

    public SimulationEntity() {
        // Automatically generate id as a String
        this.id = UUID.randomUUID().toString(); // Convert UUID to String

        // Set createdAt to the current timestamp
        this.createdAt = LocalDateTime.now();
    }

    public SimulationEntity(Simulation simulation) {
        this.id = UUID.randomUUID().toString();
        this.amount = simulation.getAmount();
        this.birthDate = simulation.getBirthDate();
        this.paymentTerm = simulation.getPaymentTerm();
        this.annualInterestRate = simulation.getAnnualInterestRate();
        this.createdAt = LocalDateTime.now();
        this.totalAmount = simulation.getTotalAmount();
        this.monthlyInstallmentAmount = simulation.getMonthlyInstallmentAmount();
        this.totalInterestAmount = simulation.getTotalInterestAmount();
        this.email = simulation.getEmail();
    }

    public SimulationEntity(UUID id, BigDecimal amount, LocalDate birthDate, int paymentTerm, double annualInterestRate,
                            LocalDateTime createdAt, BigDecimal totalAmount, BigDecimal monthlyInstallmentAmount,
                            BigDecimal totalInterestAmount, String email) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.birthDate = birthDate;
        this.paymentTerm = paymentTerm;
        this.annualInterestRate = annualInterestRate;
        this.createdAt = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
        this.totalInterestAmount = totalInterestAmount;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getMonthlyInstallmentAmount() {
        return monthlyInstallmentAmount;
    }

    public void setMonthlyInstallmentAmount(BigDecimal monthlyInstallmentAmount) {
        this.monthlyInstallmentAmount = monthlyInstallmentAmount;
    }

    public BigDecimal getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public void setTotalInterestAmount(BigDecimal totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Simulation toSimulation() {
        return new Simulation(UUID.fromString(id), totalAmount, monthlyInstallmentAmount, totalInterestAmount);
    }

    public static class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

        @Override
        public String convert(final LocalDateTime time) {
            return time.toString();
        }

        @Override
        public LocalDateTime unconvert(final String stringValue) {
            return LocalDateTime.parse(stringValue);
        }
    }

    // Converters for BigDecimal, LocalDate, and LocalDateTime
    public static class BigDecimalConverter implements DynamoDBTypeConverter<String, BigDecimal> {
        @Override
        public String convert(BigDecimal object) {
            return object.toString();
        }

        @Override
        public BigDecimal unconvert(String object) {
            return new BigDecimal(object);
        }
    }

    public static class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
        @Override
        public String convert(LocalDate object) {
            return object.toString();
        }

        @Override
        public LocalDate unconvert(String object) {
            return LocalDate.parse(object);
        }
    }
}
