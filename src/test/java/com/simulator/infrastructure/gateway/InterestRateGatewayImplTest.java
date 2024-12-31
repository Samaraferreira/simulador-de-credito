package com.simulator.infrastructure.gateway;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class InterestRateGatewayImplTest {

    private final InterestRateGatewayImpl interestRateGateway = new InterestRateGatewayImpl();

    @ParameterizedTest
    @CsvSource({
            "25, 0.05",    // Age <= 25 should return 5%
            "30, 0.03",    // Age between 26 and 40 should return 3%
            "50, 0.02",    // Age between 41 and 60 should return 2%
            "65, 0.04",    // Age > 60 should return 4%
            "24, 0.05",    // Edge case: age 24 (<= 25) should return 5%
            "40, 0.03",    // Edge case: age 40 (<= 40) should return 3%
            "60, 0.02"     // Edge case: age 60 (<= 60) should return 2%
    })
    void testGetAnnualInterestRate(int age, double expectedRate) {
        assertEquals(expectedRate, interestRateGateway.getAnnualInterestRate(age), 0.001);
    }
}