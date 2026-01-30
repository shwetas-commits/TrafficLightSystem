package com.natwest.trafficlightsystem.intersection;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.LightColor;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficPhaseTest {

    @Test
    void represents_a_green_light_for_a_direction() {
        TrafficPhase phase =
                new TrafficPhase(Direction.NORTH, LightColor.GREEN);

        assertThat(phase.direction()).isEqualTo(Direction.NORTH);
        assertThat(phase.color()).isEqualTo(LightColor.GREEN);
    }

    @Test
    void is_immutable() {
        TrafficPhase phase =
                new TrafficPhase(Direction.EAST, LightColor.RED);

        assertThat(phase.direction()).isEqualTo(Direction.EAST);
        assertThat(phase.color()).isEqualTo(LightColor.RED);
    }
}
