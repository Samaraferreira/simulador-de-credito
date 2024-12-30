package com.simulator.infrastructure.gateway;

import com.simulator.core.domain.Simulation;
import com.simulator.core.gateway.SimulationGateway;
import com.simulator.infrastructure.persistence.SimulationRepository;
import com.simulator.infrastructure.persistence.entity.SimulationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimulationGatewayImpl implements SimulationGateway {

    private final SimulationRepository simulationRepository;

    public SimulationGatewayImpl(@Autowired SimulationRepository simulationRepository) {
        this.simulationRepository = simulationRepository;
    }

    @Override
    public Simulation create(Simulation simulation) {
        SimulationEntity entity = new SimulationEntity(simulation);
        return simulationRepository.save(entity).toSimulation();
    }
}
