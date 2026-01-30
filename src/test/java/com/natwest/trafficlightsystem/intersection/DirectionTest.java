package com.natwest.trafficlightsystem.intersection;

import org.junit.jupiter.api.Test;

import static com.natwest.trafficlightsystem.domain.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void north_conflicts_with_east() {
        assertThat(NORTH.conflictsWith(EAST)).isTrue();
    }

    @Test
    void north_conflicts_with_west() {
        assertThat(NORTH.conflictsWith(WEST)).isTrue();
    }

    @Test
    void north_does_not_conflict_with_south() {
        assertThat(NORTH.conflictsWith(SOUTH)).isFalse();
    }

    @Test
    void east_does_not_conflict_with_west() {
        assertThat(EAST.conflictsWith(WEST)).isFalse();
    }
}
