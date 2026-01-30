package com.natwest.trafficlightsystem.dto;

import java.time.Instant;

public record PhaseHistoryDto(
        String direction,
        String color,
        Instant changedAt
) {}
