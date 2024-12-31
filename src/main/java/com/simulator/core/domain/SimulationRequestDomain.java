package com.simulator.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class SimulationRequestDomain {

    private BigDecimal amount;
    private LocalDate birthDate;
    private int paymentTerm;
    private String email;

    public SimulationRequestDomain(BigDecimal amount, LocalDate birthDate, int paymentTerm, String email) {
        this.amount = amount;
        this.birthDate = birthDate;
        this.paymentTerm = paymentTerm;
        this.email = email;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
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

    public String getEmail() {
        return email;
    }
}
