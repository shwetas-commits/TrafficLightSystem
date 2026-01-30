
package com.natwest.trafficlightsystem.domain;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public boolean conflictsWith(Direction other) {
        if (this == other) return false;

        boolean thisIsNS = this == NORTH || this == SOUTH;
        boolean otherIsEW = other == EAST || other == WEST;

        boolean thisIsEW = this == EAST || this == WEST;
        boolean otherIsNS = other == NORTH || other == SOUTH;

        return (thisIsNS && otherIsEW) || (thisIsEW && otherIsNS);
    }
}
