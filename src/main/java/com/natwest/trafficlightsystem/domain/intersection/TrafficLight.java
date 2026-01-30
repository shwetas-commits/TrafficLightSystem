package com.natwest.trafficlightsystem.domain.intersection;

import java.time.Instant;

public class TrafficLight {

    private final Direction direction;
    private final LightColor color;
    private final Instant since;

    public TrafficLight(Direction direction, LightColor color, Instant since) {
        this.direction = direction;
        this.color = color;
        this.since = since;
    }

    public Direction getDirection() {
        return direction;
    }

    public LightColor getColor() {
        return color;
    }

    public Instant getSince() {
        return since;
    }
}
