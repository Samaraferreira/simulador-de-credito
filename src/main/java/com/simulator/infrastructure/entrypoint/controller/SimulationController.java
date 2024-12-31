package com.simulator.infrastructure.entrypoint.controller;

import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.infrastructure.entrypoint.controller.dto.SimulationRequest;
import com.simulator.infrastructure.entrypoint.controller.dto.SimulationResponse;
import com.simulator.core.usecase.SimulationUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final SimulationUseCase simulationUseCase;

    @Autowired
    public SimulationController(SimulationUseCase simulationUseCase) {
        this.simulationUseCase = simulationUseCase;
    }

    @PostMapping
    public SimulationResponse simulate(@RequestBody @Valid SimulationRequest simulationRequest) {
        SimulationRequestDomain simulationDomain = simulationRequest.toDomain();
        return SimulationResponse.fromDomain(simulationUseCase.create(simulationDomain));
    }
}
