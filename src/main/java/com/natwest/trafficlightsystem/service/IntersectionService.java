package com.natwest.trafficlightsystem.service;

import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.domain.TrafficLight;
import com.natwest.trafficlightsystem.dto.IntersectionStateDto;
import com.natwest.trafficlightsystem.mapper.IntersectionMapper;
import com.natwest.trafficlightsystem.persistence.entity.PhaseHistory;
import com.natwest.trafficlightsystem.respository.PhaseHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

@Service
public class IntersectionService {

    private final InMemoryIntersectionRegistry registry;
    private final PhaseHistoryRepository historyRepository;

    // 🔥 Per intersection lock
    private final Map<String, Lock> locks = new ConcurrentHashMap<>();

    public IntersectionService(InMemoryIntersectionRegistry registry,
                               PhaseHistoryRepository historyRepository) {
        this.registry = registry;
        this.historyRepository = historyRepository;
    }

    /* =============================
       PUBLIC API METHODS
       ============================= */

    public IntersectionStateDto getState(String id) {
        Intersection intersection = registry.get(id);
        return IntersectionMapper.toStateDto(intersection);
    }

    public void start(String id) {
        withLock(id, Intersection::start);
    }

    public void pause(String id) {
        withLock(id, Intersection::pause);
    }

    public void resume(String id) {
        withLock(id, Intersection::resume);
    }

    public void updateSequence(String id, List<com.natwest.trafficlightsystem.domain.TrafficPhase> phases) {
        withLock(id, intersection -> intersection.updateSequence(phases));
    }

    public List<PhaseHistory> getHistory(String id) {
        return historyRepository.findTop1000ByIntersectionIdOrderByChangedAtDesc(id);
    }

    /* =============================
       SCHEDULER ENTRY POINT
       ============================= */

    public void advancePhase(String id) {
        withLock(id, intersection -> {
            intersection.advancePhase();
            persistHistory(intersection);
        });
    }

    /* =============================
       INTERNAL HELPERS
       ============================= */

    private void persistHistory(Intersection intersection) {

        for (TrafficLight light : intersection.currentLights()) {

            PhaseHistory history = new PhaseHistory(
                    intersection.getId(),
                    light.getDirection(),
                    light.getColor(),
                    light.getSince()
            );

            historyRepository.save(history);
        }
    }

    private void withLock(String id, Consumer<Intersection> action) {
        Lock lock = locks.computeIfAbsent(id, k -> new ReentrantLock());
        lock.lock();
        try {
            Intersection intersection = registry.get(id);
            action.accept(intersection);
        } finally {
            lock.unlock();
        }
    }
}

