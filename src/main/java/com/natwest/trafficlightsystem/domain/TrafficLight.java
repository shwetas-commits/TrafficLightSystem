package com.natwest.trafficlightsystem.domain;

import java.time.Instant;

public class TrafficLight {

    private final Direction direction;
    private LightColor color;
    private Instant since;

    public TrafficLight(Direction direction, LightColor color) {
        this.direction = direction;
        this.color = color;
        this.since = Instant.now();
    }

    public void changeColor(LightColor newColor) {
        if (this.color != newColor) {
            this.color = newColor;
            this.since = Instant.now();
        }
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

