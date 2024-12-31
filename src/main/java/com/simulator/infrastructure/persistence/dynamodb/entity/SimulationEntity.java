package com.simulator.infrastructure.persistence.dynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.builder.SimulationBuilder;
import com.simulator.infrastructure.persistence.dynamodb.converters.BigDecimalConverter;
import com.simulator.infrastructure.persistence.dynamodb.converters.LocalDateConverter;
import com.simulator.infrastructure.persistence.dynamodb.converters.LocalDateTimeConverter;

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
    private BigDecimal monthlyPayment;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = BigDecimalConverter.class)
    private BigDecimal totalInterestAmount;

    @DynamoDBAttribute
    private String email;

    public SimulationEntity(BigDecimal amount, LocalDate birthDate, int paymentTerm, double annualInterestRate, BigDecimal totalAmount, BigDecimal monthlyPayment, BigDecimal totalInterestAmount, String email) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.birthDate = birthDate;
        this.paymentTerm = paymentTerm;
        this.annualInterestRate = annualInterestRate;
        this.createdAt = LocalDateTime.now();
        this.totalAmount = totalAmount;
        this.monthlyPayment = monthlyPayment;
        this.totalInterestAmount = totalInterestAmount;
        this.email = email;
    }

    public static SimulationEntity fromDomain(SimulationDomain simulationDomain) {
        return new SimulationEntity(
                simulationDomain.getAmount(),
                simulationDomain.getBirthDate(),
                simulationDomain.getPaymentTerm(),
                simulationDomain.getAnnualInterestRate(),
                simulationDomain.getTotalAmount(),
                simulationDomain.getMonthlyPayment(),
                simulationDomain.getTotalInterestAmount(),
                simulationDomain.getEmail()
        );
    }

    public SimulationDomain toDomain() {
        return new SimulationBuilder()
                .id(UUID.fromString(id))
                .amount(amount)
                .birthDate(birthDate)
                .paymentTerm(paymentTerm)
                .annualInterestRate(annualInterestRate)
                .totalAmount(totalAmount)
                .monthlyPayment(monthlyPayment)
                .totalInterestAmount(totalInterestAmount)
                .email(email)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public String getId() {
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
}
