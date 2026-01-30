package com.natwest.trafficlightsystem.intersection;

import java.util.Set;

public class TrafficPhase {

    private final Set<Direction> greenDirections;

    public TrafficPhase(Set<Direction> greenDirections) {
        validate(greenDirections);
        this.greenDirections = Set.copyOf(greenDirections);
    }

    private void validate(Set<Direction> directions) {
        for (Direction d1 : directions) {
            for (Direction d2 : directions) {
                if (d1.conflictsWith(d2)) {
                    throw new IllegalArgumentException(
                            "Conflicting directions cannot be green together: "
                                    + d1 + " and " + d2);
                }
            }
        }
    }

    public Set<Direction> getGreenDirections() {
        return greenDirections;
    }
}
