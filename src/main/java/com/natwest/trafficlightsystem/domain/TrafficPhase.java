package com.natwest.trafficlightsystem.domain;

import java.util.Set;
public record TrafficPhase(
        Direction direction,
        LightColor color
) {
    public boolean isGreen() {
        return color == LightColor.GREEN;
    }
}
