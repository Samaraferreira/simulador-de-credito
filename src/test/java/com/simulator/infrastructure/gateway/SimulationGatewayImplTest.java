package com.simulator.infrastructure.gateway;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.infrastructure.persistence.dynamodb.entity.SimulationEntity;
import com.simulator.infrastructure.persistence.dynamodb.repository.SimulationRepository;
import com.simulator.mock.SimulationMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SimulationGatewayImplTest {

    @Mock
    private SimulationRepository simulationRepository;

    @InjectMocks
    private SimulationGatewayImpl simulationGateway;

    @BeforeEach
    void setUp() {
        simulationRepository = mock(SimulationRepository.class);
        simulationGateway = new SimulationGatewayImpl(simulationRepository);
    }

    @Test
    void testCreate() {
        SimulationDomain simulationDomain = SimulationMock.getMockedSimulation();
        SimulationEntity simulationEntity = SimulationEntity.fromDomain(simulationDomain);
        when(simulationRepository.save(any(SimulationEntity.class))).thenReturn(simulationEntity);

        SimulationDomain result = simulationGateway.create(simulationDomain);

        assertNotNull(result.getId());
        assertNotNull(result.getCreatedAt());
        assertEquals(simulationDomain.getMonthlyPayment(), result.getMonthlyPayment());
        assertEquals(simulationDomain.getTotalAmount(), result.getTotalAmount());
        verify(simulationRepository, times(1)).save(any(SimulationEntity.class));
    }

}