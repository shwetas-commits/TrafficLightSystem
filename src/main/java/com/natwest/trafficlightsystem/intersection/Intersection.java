package com.natwest.trafficlightsystem.intersection;

import java.util.List;
import java.util.Set;

public class Intersection {

    private final String id;
    private final List<TrafficPhase> phases;
    private int currentPhaseIndex = 0;
    private boolean paused = false;

    public Intersection(String id, List<TrafficPhase> phases) {
        this.id = id;
        this.phases = List.copyOf(phases);
    }

    public TrafficPhase getCurrentPhase() {
        return phases.get(currentPhaseIndex);
    }

    // If the intersection is running, move to the next traffic light phase
    //If we’re already at the last phase, loop back to the first one
    public void advancePhase() {
        if (paused) return; //if the traffic light system is paused, do nothing
        currentPhaseIndex = (currentPhaseIndex + 1) % phases.size(); // move to the next phase in circular sequence(0-1-2-0..)
    }

    public Set<Direction> getAllDirections() {
        return Set.of(Direction.NORTH, Direction.SOUTH,
                Direction.EAST, Direction.WEST);
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public String getId() {
        return id;
    }
}
