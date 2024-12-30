package com.simulator.infrastructure.gateway;

import com.simulator.core.domain.Simulation;
import com.simulator.core.gateway.NotificationGateway;
import org.springframework.stereotype.Component;

@Component
public class NotificationGatewayImpl implements NotificationGateway {

    @Override
    public void notify(Simulation simulation) {
        System.out.println("Notificação enviada para a proposta: " + simulation.getId());
    }
}
