package com.natwest.trafficlightsystem.intersection;

import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.LightColor;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntersectionTest {

    @Test
    void advances_to_next_phase_when_not_paused() {
        // Use non-conflicting directions for green
        TrafficPhase phase1 = new TrafficPhase(Direction.NORTH, LightColor.GREEN);
        TrafficPhase phase2 = new TrafficPhase(Direction.SOUTH, LightColor.YELLOW);

        Intersection intersection = new Intersection("I1", List.of(phase1, phase2));
        intersection.start(); // initialize

        intersection.advancePhase();

        assertThat(intersection.currentPhase()).isEqualTo(phase2);
    }

    @Test
    void does_not_advance_when_paused() {
        TrafficPhase phase = new TrafficPhase(Direction.NORTH, LightColor.GREEN);

        Intersection intersection = new Intersection("I1", List.of(phase));
        intersection.start();
        intersection.pause();

        intersection.advancePhase();

        assertThat(intersection.currentPhase()).isEqualTo(phase);
    }

    @Test
    void advancePhase_should_not_change_when_paused_multiple_calls() {
        TrafficPhase phase = new TrafficPhase(Direction.NORTH, LightColor.GREEN);

        Intersection intersection = new Intersection("I1", List.of(phase));
        intersection.start();
        intersection.pause();

        intersection.advancePhase();
        intersection.advancePhase();

        assertThat(intersection.currentPhase()).isEqualTo(phase);
    }

    @Test
    void rejects_conflicting_green_phases() {
        assertThatThrownBy(() -> new Intersection("I1", List.of(
                new TrafficPhase(Direction.NORTH, LightColor.GREEN),
                new TrafficPhase(Direction.EAST, LightColor.GREEN)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Conflicting directions cannot be green simultaneously");
    }
}
