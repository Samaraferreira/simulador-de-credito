package com.simulator.infrastructure.gateway;

import com.simulator.core.gateway.InterestRateGateway;
import org.springframework.stereotype.Component;

@Component
public class InterestRateGatewayImpl implements InterestRateGateway {

    public double getAnnualInterestRate(int age) {
        if (age <= 25) return 0.05;
        else if (age <= 40) return 0.03;
        else if (age <= 60) return 0.02;
        else return 0.04;
    }
}
