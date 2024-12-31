package com.simulator.infrastructure.entrypoint.controller.dto;

import com.simulator.core.domain.SimulationRequestDomain;
import com.simulator.core.domain.builder.SimulationRequestBuilder;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SimulationRequest(
        @NotNull(message = "The paymentTerm cannot be null.")
        int paymentTerm,

        @NotNull(message = "The date cannot be null.")
        @Past(message = "The date must be in the past.")
        LocalDate birthDate,

        @NotNull(message = "The paymentTerm cannot be null.")
        @Positive
        BigDecimal amount,

        @NotEmpty @Email String email
) {

        public SimulationRequestDomain toDomain() {
                return new SimulationRequestBuilder()
                        .amount(amount)
                        .birthDate(birthDate)
                        .paymentTerm(paymentTerm)
                        .email(email)
                        .build();
        }
}
