package com.simulator.core.domain.builder;

import com.simulator.core.domain.SimulationRequestDomain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SimulationRequestBuilder {

    private BigDecimal amount;
    private LocalDate birthDate;
    private int paymentTerm;
    private String email;

    public SimulationRequestBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public SimulationRequestBuilder birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public SimulationRequestBuilder paymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
        return this;
    }

    public SimulationRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public SimulationRequestDomain build() {
        return new SimulationRequestDomain(amount, birthDate, paymentTerm, email);
    }
}