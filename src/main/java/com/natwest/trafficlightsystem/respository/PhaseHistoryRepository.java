package com.natwest.trafficlightsystem.respository;

import com.natwest.trafficlightsystem.persistence.entity.PhaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhaseHistoryRepository
        extends JpaRepository<PhaseHistory, Long> {

    List<PhaseHistory> findByIntersectionId(String intersectionId);

    List<PhaseHistory> findTop1000ByIntersectionIdOrderByChangedAtDesc(String intersectionId);

    //PageRequest.of(0, 1000, Sort.by("timestamp").descending())
}
