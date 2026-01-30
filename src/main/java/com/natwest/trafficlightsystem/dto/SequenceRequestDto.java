package com.natwest.trafficlightsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record SequenceRequestDto(

        @NotEmpty(message = "Phase sequence must not be empty")
        List<PhaseDto> phases

) {}
