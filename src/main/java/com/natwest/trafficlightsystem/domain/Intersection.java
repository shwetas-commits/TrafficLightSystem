package com.natwest.trafficlightsystem.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.*;

public class Intersection {

    private final String id;

    private boolean paused;
    private boolean started;

    private int currentPhaseIndex;
    private List<TrafficPhase> phases;

    private final Map<Direction, TrafficLight> lights = new EnumMap<>(Direction.class);

    public Intersection(String id, List<TrafficPhase> phases) {
        this.id = Objects.requireNonNull(id);
        validatePhases(phases);
        this.phases = new ArrayList<>(phases);

        // initialize all lights as RED
        for (Direction direction : Direction.values()) {
            lights.put(direction, new TrafficLight(direction, LightColor.RED));
        }

        applyPhase(phases.get(0));
    }

    /* ----------------- lifecycle commands ----------------- */

    public void start() {
        if (started) {
            throw new IllegalStateException("Already started");
        }
        this.started = true;
        this.paused = false;
    }

    public void pause() {
            ensureStarted();
        this.paused = true;
    }

    public void resume() {
        ensureStarted();
        this.paused = false;
    }

    //phase application logic - lights change color and update since

    private void applyPhase(TrafficPhase phase) {
        Set<Direction> green = phase.getGreenDirections();

        for (TrafficLight light : lights.values()) {
            if (green.contains(light.getDirection())) {
                light.changeColor(LightColor.GREEN);
            } else {
                light.changeColor(LightColor.RED);
            }
        }
    }

    /* ----------------- phase progression ----------------- */

    public void advancePhase() {
        ensureStarted();
        if (paused) return;

        currentPhaseIndex = (currentPhaseIndex + 1) % phases.size();
        applyPhase(phases.get(currentPhaseIndex)); // real state transition.
    }

    public void updateSequence(List<TrafficPhase> newPhases) {
        validatePhases(newPhases);
        this.phases = new ArrayList<>(newPhases);
        this.currentPhaseIndex = 0;
    }

    /* ----------------- queries ----------------- */

    public TrafficPhase currentPhase() {
        return phases.get(currentPhaseIndex);
    }

    public List<TrafficPhase> allPhases() {
        return List.copyOf(phases);
    }

    public Collection<TrafficLight> currentLights() {
        return List.copyOf(lights.values());
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isStarted() {
        return started;
    }

    public String getId() {
        return id;
    }

    /* ----------------- invariants ----------------- */

    private void ensureStarted() {
        if (!started) {
            throw new IllegalStateException("Intersection not started");
        }
    }

    //validates phase sequence correctness
    private void validatePhases(List<TrafficPhase> phases) {
        if (phases == null || phases.isEmpty()) {
            throw new IllegalArgumentException("Traffic phase sequence cannot be empty");
        }

        for (TrafficPhase phase : phases) {
            if (phase.getGreenDirections() == null || phase.getGreenDirections().isEmpty()) {
                throw new IllegalArgumentException("Each phase must have at least one green direction");
            }
        }
    }

}
