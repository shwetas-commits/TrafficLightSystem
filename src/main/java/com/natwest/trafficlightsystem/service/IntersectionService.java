package com.natwest.trafficlightsystem.service;

import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import com.natwest.trafficlightsystem.dto.IntersectionStateDto;
import com.natwest.trafficlightsystem.dto.IntersectionMapper;
import com.natwest.trafficlightsystem.dto.PhaseHistory;
import com.natwest.trafficlightsystem.respository.PhaseHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class IntersectionService {

    private final InMemoryIntersectionRegistry registry;
    private final PhaseHistoryRepository historyRepository;
    private final Lock lock = new ReentrantLock();

    //can be moved to per-intersection later

    public IntersectionService(InMemoryIntersectionRegistry registry,
                               PhaseHistoryRepository historyRepository) {
        this.registry = registry;
        this.historyRepository = historyRepository;
    }

    public IntersectionStateDto getState(String id) {
        Intersection intersection = registry.get(id);
        return IntersectionMapper.toStateDto(intersection);
    }


    public void start(String intersectionId) {
        withLock(intersectionId, Intersection::start);
    }

    public void pause(String intersectionId) {
        withLock(intersectionId, Intersection::pause);
    }

    public void resume(String intersectionId) {
        withLock(intersectionId, Intersection::resume);
    }

    public List<PhaseHistory> getHistory(String intersectionId) {
        return historyRepository.findByIntersectionId(intersectionId);
    }


    /** INTERNAL – called by scheduler */
    public void advancePhase(String intersectionId) {
        withLock(intersectionId, Intersection::advancePhase);
    }

    public void updateSequence(String id, List<TrafficPhase> phases) {
        lock.lock();
        try {
            registry.get(id).updateSequence(phases);
        } finally {
            lock.unlock();
        }
    }

    //helper

    private void withLock(String intersectionId, java.util.function.Consumer<Intersection> action) {
        lock.lock();
        try {
            Intersection intersection = registry.get(intersectionId);
            action.accept(intersection);
        } finally {
            lock.unlock();
        }
    }
}
