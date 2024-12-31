package com.simulator.core.usecase.impl;

import com.simulator.core.domain.SimulationDomain;
import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationRequestBuilder;
import com.simulator.core.gateway.InterestRateGateway;
import com.simulator.core.gateway.NotificationGateway;
import com.simulator.core.gateway.SimulationGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.simulator.mock.SimulationMock.createSimulation;
import static com.simulator.utils.Utils.round;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SimulationDomainUseCaseImplTest {

    @Mock
    private NotificationGateway notificationGateway;

    @Mock
    private SimulationGateway simulationGateway;

    @Mock
    private InterestRateGateway interestRateGateway;

    @InjectMocks
    private SimulationUseCaseImpl simulationUseCase;

    @BeforeEach
    void setUp() {
        notificationGateway = mock(NotificationGateway.class);
        simulationGateway = mock(SimulationGateway.class);
        interestRateGateway = mock(InterestRateGateway.class);
        simulationUseCase = new SimulationUseCaseImpl(notificationGateway, simulationGateway, interestRateGateway);
    }

    @ParameterizedTest
    @MethodSource("provideSimulations")
    public void testCreateSimulation(SimulationDomain expected) {
        SimulationRequestDomain mockedRequest = new SimulationRequestBuilder()
                .amount(expected.getAmount())
                .birthDate(expected.getBirthDate())
                .paymentTerm(expected.getPaymentTerm())
                .email(expected.getEmail())
                .build();

        when(interestRateGateway.getAnnualInterestRate(mockedRequest.getAge()))
                .thenReturn(expected.getAnnualInterestRate());
        when(simulationGateway.create(any(SimulationDomain.class)))
                .thenReturn(any(SimulationDomain.class));

        SimulationDomain result = simulationUseCase.create(mockedRequest);

        assertNotNull(result);
        assertEquals(round(expected.getTotalAmount()), result.getTotalAmount());
        assertEquals(round(expected.getMonthlyPayment()), result.getMonthlyPayment());
        assertEquals(round(expected.getTotalInterestAmount()), result.getTotalInterestAmount());

        verify(notificationGateway).notify(result);
        verify(simulationGateway).create(result);
    }

    public static Stream<SimulationDomain> provideSimulations() {
        return Stream.of(
                createSimulation(24, 0.05, BigDecimal.valueOf(856.07), BigDecimal.valueOf(272.90),
                        BigDecimal.valueOf(10272.90)),
                createSimulation(26, 0.02, BigDecimal.valueOf(842.39), BigDecimal.valueOf(108.66),
                        BigDecimal.valueOf(10108.66)),
                createSimulation(55, 0.03, BigDecimal.valueOf(846.94), BigDecimal.valueOf(163.24),
                        BigDecimal.valueOf(10163.24)),
                createSimulation(61, 0.04, BigDecimal.valueOf(851.50), BigDecimal.valueOf(217.99),
                        BigDecimal.valueOf(10217.99))
        );
    }


}