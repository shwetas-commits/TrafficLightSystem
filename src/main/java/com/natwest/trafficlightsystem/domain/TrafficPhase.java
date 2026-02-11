package com.natwest.trafficlightsystem.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrafficPhase {

    private final Set<Direction> greenDirections;

    public TrafficPhase(Set<Direction> greenDirections) {
        this.greenDirections = Set.copyOf(greenDirections);
        validate(this.greenDirections);
    }

    //validates internal green direction conflicts
    private void validate(Set<Direction> directions) {
        List<Direction> list = new ArrayList<>(directions);
        //each pair checked only once
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Direction d1 = list.get(i);
                Direction d2 = list.get(j);

                if (d1.conflictsWith(d2)) {
                    throw new IllegalArgumentException(
                            "Conflicting directions cannot be green simultaneously"
                    );
                }
            }
        }
    }

    public Set<Direction> getGreenDirections() {
        return greenDirections;
    }
}
