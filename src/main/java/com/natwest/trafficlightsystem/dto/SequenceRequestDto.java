package com.natwest.trafficlightsystem.dto;

import java.util.List;

public record SequenceRequestDto(
        List<PhaseRequest> phases
) {
        public record PhaseRequest(
                List<String> greenDirections
        ) {}
}
