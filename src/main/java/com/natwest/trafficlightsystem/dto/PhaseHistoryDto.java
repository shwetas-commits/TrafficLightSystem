package com.natwest.trafficlightsystem.dto;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.LightColor;

import java.time.Instant;

public record PhaseHistoryDto(
        Direction direction,
        LightColor color,
        Instant changedAt
) {}
