package com.natwest.trafficlightsystem.dto;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.LightColor;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "phase_history")
public class PhaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String intersectionId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LightColor color;

    @Column(nullable = false)
    private Instant changedAt;

    protected PhaseHistory() {
        // JPA
    }

    public PhaseHistory(
            String intersectionId,
            Direction direction,
            LightColor color,
            Instant changedAt
    ) {
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
