package com.simulator.adapters.entrypoint;

import com.simulator.adapters.entrypoint.dto.SimulationRequest;
import com.simulator.adapters.entrypoint.dto.SimulationResponse;
import com.simulator.core.domain.Simulation;
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
        var proposal = new Simulation(simulationRequest.amount(),
                simulationRequest.birthDate(),
                simulationRequest.paymentTerm(), simulationRequest.email());
        Simulation simulation = simulationUseCase.create(proposal);
        return SimulationResponse.fromSimulation(simulation);
    }
}
