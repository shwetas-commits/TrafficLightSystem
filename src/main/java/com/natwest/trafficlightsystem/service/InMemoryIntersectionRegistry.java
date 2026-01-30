package com.natwest.trafficlightsystem.service;

import com.natwest.trafficlightsystem.domain.intersection.Intersection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryIntersectionRegistry {

    private final Map<String, Intersection> store = new ConcurrentHashMap<>();

    public Intersection get(String id) {
        return store.get(id);
    }

    public void save(Intersection intersection) {
        store.put(intersection.getId(), intersection);
    }
}
