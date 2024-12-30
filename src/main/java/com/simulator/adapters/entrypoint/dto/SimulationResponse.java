package com.simulator.adapters.entrypoint.dto;

import com.simulator.core.domain.Simulation;

import java.math.BigDecimal;

public record SimulationResponse(BigDecimal totalAmount,
        BigDecimal monthlyInstallmentAmount,
        BigDecimal totalInterestAmount) {

    public static SimulationResponse fromSimulation(Simulation simulation) {
        return new SimulationResponse(
                simulation.getTotalAmount(),
                simulation.getMonthlyInstallmentAmount(),
                simulation.getTotalInterestAmount()
        );
    }
}
