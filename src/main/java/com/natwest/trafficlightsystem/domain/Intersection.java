package com.natwest.trafficlightsystem.domain;

import com.natwest.trafficlightsystem.domain.TrafficPhase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Intersection {

    private final String id;

    private boolean paused;
    private boolean started;

    private int currentPhaseIndex;
    private List<TrafficPhase> phases;

    public Intersection(String id, List<TrafficPhase> phases) {
        this.id = Objects.requireNonNull(id, "intersection id cannot be null");
        validatePhases(phases);
        this.phases = new ArrayList<>(phases);
        this.currentPhaseIndex = 0;
        this.paused = true;     // safe default
        this.started = false;
    }

    /* ----------------- lifecycle commands ----------------- */

    public void start() {
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

    /* ----------------- phase progression ----------------- */

    public void advancePhase() {
        ensureStarted();
        if (paused) {
            return;
        }
        currentPhaseIndex = (currentPhaseIndex + 1) % phases.size();
    }

    public List<TrafficPhase> getCurrentPhases() {
        return List.copyOf(phases);
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

    private void validatePhases(List<TrafficPhase> phases) {
        if (phases == null || phases.isEmpty()) {
            throw new IllegalArgumentException("Traffic phase sequence cannot be empty");
        }

        long greenCount =
                phases.stream()
                        .filter(TrafficPhase::isGreen)
                        .map(TrafficPhase::direction)
                        .distinct()
                        .count();

        if (greenCount > 1) {
            throw new IllegalArgumentException(
                    "Conflicting directions cannot be green simultaneously"
            );
        }
    }
}
