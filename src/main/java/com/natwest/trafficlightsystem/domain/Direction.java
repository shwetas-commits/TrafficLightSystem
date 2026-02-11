
package com.natwest.trafficlightsystem.domain;

public enum Direction {
    NORTH, // NORTH(Axis.VERTICAL)
    SOUTH, // SOUTH(Axis.VERTICAL)
    EAST, // EAST(Axis.HORIZONTAL)
    WEST; // WEST(Axis.HORIZONTAL)

    public boolean conflictsWith(Direction other) {
        if (this == other) return false;

        boolean thisIsNS = this == NORTH || this == SOUTH;
        boolean otherIsEW = other == EAST || other == WEST;

        boolean thisIsEW = this == EAST || this == WEST;
        boolean otherIsNS = other == NORTH || other == SOUTH;

        return (thisIsNS && otherIsEW) || (thisIsEW && otherIsNS);
    }

    /*
    private final Axis axis;

    Direction(Axis axis) {
        this.axis = axis;
    }

    public boolean conflictsWith(Direction other) {
        return this.axis != other.axis;
    }*/
}
