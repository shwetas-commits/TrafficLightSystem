package com.natwest.trafficlightsystem.persistence.entity;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.LightColor;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class PhaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String intersectionId;

    private Direction direction;

    private LightColor color;

    private Instant changedAt;

    protected PhaseHistory() {}

    public PhaseHistory(String intersectionId,
                        Direction direction,
                        LightColor color,
                        Instant changedAt) {
        this.intersectionId = intersectionId;
        this.direction = direction;
        this.color = color;
        this.changedAt = changedAt;
    }

    // getters only //immutable entityy
    public Long getId() { return id; }
    public String getIntersectionId() { return intersectionId; }
    public Direction getDirection() { return direction; }
    public LightColor getColor() { return color; }
    public Instant getChangedAt() { return changedAt; }
}
