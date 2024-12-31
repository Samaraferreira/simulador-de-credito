package com.simulator.infrastructure.entrypoint.controller.dto;

import com.simulator.core.domain.SimulationDomain;

import java.math.BigDecimal;

public record SimulationResponse(BigDecimal totalAmount,
                                 BigDecimal monthlyPayment,
                                 BigDecimal totalInterestAmount) {

    public static SimulationResponse fromDomain(SimulationDomain simulationDomain) {
        return new SimulationResponse(
                simulationDomain.getTotalAmount(),
                simulationDomain.getMonthlyPayment(),
                simulationDomain.getTotalInterestAmount()
        );
    }
}
