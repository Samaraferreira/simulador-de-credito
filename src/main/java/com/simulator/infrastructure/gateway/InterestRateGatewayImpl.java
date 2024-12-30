package com.simulator.infrastructure.gateway;

import com.simulator.core.gateway.InterestRateGateway;
import org.springframework.stereotype.Component;

@Component
public class InterestRateGatewayImpl implements InterestRateGateway {

    public double getAnnualInterestRate(int age) {
        if (age <= 25) return 5.0/100;
        else if (age <= 40) return 3.0/100;
        else if (age <= 60) return 2.0/100;
        else return 4.0/100;
    }
}
