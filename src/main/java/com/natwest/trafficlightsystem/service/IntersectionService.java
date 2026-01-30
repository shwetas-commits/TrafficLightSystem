package com.natwest.trafficlightsystem.service;

import com.natwest.trafficlightsystem.domain.intersection.Intersection;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class IntersectionService {

    private final InMemoryIntersectionRegistry registry;
    private final Lock lock = new ReentrantLock(); //mutual-exclusion lock - only one thread at a time can enter this section

    public IntersectionService(InMemoryIntersectionRegistry registry) {
        this.registry = registry;
    }

    public Intersection getIntersection(String id) {
        return registry.get(id);
    }

    //only one thread can modify intersection state at a time

    public void advancePhase(String intersectionId) {
        lock.lock();
        try {
            Intersection intersection = registry.get(intersectionId); // fetches the in-memory value for this intersection
            intersection.advancePhase();
        } finally {
            lock.unlock(); //releases the lock
        }
    }

    public void pause(String intersectionId) {
        lock.lock();
        try {
            registry.get(intersectionId).pause();
        } finally {
            lock.unlock();
        }
    }

    public void resume(String intersectionId) {
        lock.lock();
        try {
            registry.get(intersectionId).resume();
        } finally {
            lock.unlock();
        }
    }
}
