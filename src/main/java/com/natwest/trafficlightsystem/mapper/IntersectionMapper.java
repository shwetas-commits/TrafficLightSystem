package com.natwest.trafficlightsystem.mapper;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.domain.LightColor;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import com.natwest.trafficlightsystem.dto.IntersectionStateDto;
import com.natwest.trafficlightsystem.dto.LightStateDto;
import com.natwest.trafficlightsystem.dto.PhaseHistoryDto;
import com.natwest.trafficlightsystem.dto.SequenceRequestDto;
import com.natwest.trafficlightsystem.persistence.entity.PhaseHistory;

import java.util.List;

public final class IntersectionMapper {

    private IntersectionMapper() {}

    /*REQUEST → DOMAIN */

    public static List<TrafficPhase> toDomainPhases(SequenceRequestDto request) {
        return request.phases()
                .stream()
                .map(p -> new TrafficPhase(
                        Direction.valueOf(p.direction()),
                        LightColor.valueOf(p.color())
                ))
                .toList();
    }

    /* INTERSECTION → STATE DTO */

    public static IntersectionStateDto toStateDto(Intersection intersection) {

        List<LightStateDto> lights =
                intersection.getCurrentPhases()
                        .stream()
                        .map(phase ->
                                new LightStateDto(
                                        phase.direction().name(),
                                        phase.color().name()
                                )
                        )
                        .toList();

        return new IntersectionStateDto(
                intersection.getId(),
                intersection.isPaused(),
                lights
        );
    }

    /* DOMAIN → RESPONSE DTO */

    public static PhaseHistoryDto toDto(PhaseHistory history) {
        return new PhaseHistoryDto(
                history.getDirection().name(),
                history.getColor().name(),
                history.getChangedAt()
        );
    }

      /* =======================
       HISTORY → DTO
       ======================= */

    public static List<PhaseHistoryDto> toHistoryDtos(List<PhaseHistory> history) {
        return history.stream()
                .map(h -> new PhaseHistoryDto(
                        h.getDirection().name(),
                        h.getColor().name(),
                        h.getChangedAt()
                ))
                .toList();
    }
}
