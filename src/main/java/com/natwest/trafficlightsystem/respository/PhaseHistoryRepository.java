package com.natwest.trafficlightsystem.respository;

import com.natwest.trafficlightsystem.dto.PhaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhaseHistoryRepository
        extends JpaRepository<PhaseHistory, Long> {

    List<PhaseHistory> findByIntersectionId(String intersectionId);
}
