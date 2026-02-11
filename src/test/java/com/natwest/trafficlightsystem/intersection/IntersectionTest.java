package com.natwest.trafficlightsystem.intersection;

import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
class IntersectionTest {

    @Test
    void advances_to_next_phase_when_not_paused() {

        TrafficPhase phase1 =
                new TrafficPhase(Set.of(Direction.NORTH, Direction.SOUTH));

        TrafficPhase phase2 =
                new TrafficPhase(Set.of(Direction.EAST, Direction.WEST));

        Intersection intersection =
                new Intersection("I1", List.of(phase1, phase2));

        intersection.start();
        intersection.advancePhase();

        assertThat(intersection.currentPhase())
                .isEqualTo(phase2);
    }

    @Test
    void does_not_advance_when_paused() {

        TrafficPhase phase =
                new TrafficPhase(Set.of(Direction.NORTH));

        Intersection intersection =
                new Intersection("I1", List.of(phase));

        intersection.start();
        intersection.pause();

        intersection.advancePhase();

        assertThat(intersection.currentPhase())
                .isEqualTo(phase);
    }

    @Test
    void advancePhase_should_not_change_when_paused_multiple_calls() {

        TrafficPhase phase =
                new TrafficPhase(Set.of(Direction.NORTH));

        Intersection intersection =
                new Intersection("I1", List.of(phase));

        intersection.start();
        intersection.pause();

        intersection.advancePhase();
        intersection.advancePhase();

        assertThat(intersection.currentPhase())
                .isEqualTo(phase);
    }

    @Test
    void rejects_conflicting_green_directions_in_same_phase() {

        assertThatThrownBy(() ->
                new Intersection("I1", List.of(
                        new TrafficPhase(Set.of(Direction.NORTH, Direction.EAST))
                ))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Conflicting directions cannot be green simultaneously");
    }
}
