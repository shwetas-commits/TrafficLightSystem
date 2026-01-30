package com.natwest.trafficlightsystem.dto;

import jakarta.validation.constraints.NotBlank;

public record PhaseDto(

        @NotBlank(message = "Direction is required")
        String direction,

        @NotBlank(message = "Color is required")
        String color

) {}
