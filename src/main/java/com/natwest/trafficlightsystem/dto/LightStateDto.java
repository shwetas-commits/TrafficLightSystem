package com.natwest.trafficlightsystem.dto;

import java.time.Instant;

public record LightStateDto(
        String direction,
        String color,
        Instant since
) {}
