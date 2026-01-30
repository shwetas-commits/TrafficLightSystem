package com.natwest.trafficlightsystem.dto;

import java.util.List;

public record IntersectionStateDto(
        String intersectionId,
        boolean paused,
        List<LightStateDto> lights
) {}
