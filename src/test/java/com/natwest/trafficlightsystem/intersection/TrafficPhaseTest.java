package com.natwest.trafficlightsystem.intersection;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
class TrafficPhaseTest {

    @Test
    void represents_green_directions() {
        TrafficPhase phase =
                new TrafficPhase(Set.of(Direction.NORTH, Direction.SOUTH));

        assertThat(phase.getGreenDirections())
                .containsExactlyInAnyOrder(Direction.NORTH, Direction.SOUTH);
    }

    @Test
    void is_immutable() {
        TrafficPhase phase =
                new TrafficPhase(Set.of(Direction.EAST));

        assertThat(phase.getGreenDirections())
                .containsExactly(Direction.EAST);
    }
}
