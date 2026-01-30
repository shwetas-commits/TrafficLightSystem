package com.natwest.trafficlightsystem.intersection;

import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.respository.PhaseHistoryRepository;
import com.natwest.trafficlightsystem.service.InMemoryIntersectionRegistry;
import com.natwest.trafficlightsystem.service.IntersectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IntersectionServiceTest {

    @Mock
    InMemoryIntersectionRegistry registry;

    @Mock
    PhaseHistoryRepository historyRepository;

    @Mock
    Intersection intersection;

    IntersectionService service;

    @BeforeEach
    void setUp() {
        service = new IntersectionService(registry, historyRepository);
        when(registry.get("I1")).thenReturn(intersection);
    }

    @Test
    void start_shouldDelegateToDomain() {
        service.start("I1");
        verify(intersection).start();
    }

    @Test
    void pause_shouldDelegateToDomain() {
        service.pause("I1");
        verify(intersection).pause();
    }

    @Test
    void resume_shouldDelegateToDomain() {
        service.resume("I1");
        verify(intersection).resume();
    }

    @Test
    void advancePhase_shouldDelegateToDomain() {
        service.advancePhase("I1");
        verify(intersection).advancePhase();
    }
}
