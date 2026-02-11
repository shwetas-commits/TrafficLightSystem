package com.natwest.trafficlightsystem.mapper;

import com.natwest.trafficlightsystem.domain.Direction;
import com.natwest.trafficlightsystem.domain.Intersection;
import com.natwest.trafficlightsystem.domain.TrafficPhase;
import com.natwest.trafficlightsystem.dto.IntersectionStateDto;
import com.natwest.trafficlightsystem.dto.LightStateDto;
import com.natwest.trafficlightsystem.dto.PhaseHistoryDto;
import com.natwest.trafficlightsystem.dto.SequenceRequestDto;
import com.natwest.trafficlightsystem.persistence.entity.PhaseHistory;

import java.util.List;
import java.util.Set;

public final class IntersectionMapper {

    private IntersectionMapper() {}

    /* =============================
       REQUEST → DOMAIN
       ============================= */

    public static List<TrafficPhase> toDomainPhases(SequenceRequestDto request) {
        return request.phases()
                .stream()
                .map(p -> new TrafficPhase(
                        Set.copyOf(
                                p.greenDirections()
                                        .stream()
                                        .map(Direction::valueOf)
                                        .toList()
                        )
                ))
                .toList();
    }

    /* =============================
       DOMAIN → STATE DTO
       ============================= */

    public static IntersectionStateDto toStateDto(Intersection intersection) {

        List<LightStateDto> lights =
                intersection.currentLights()
                        .stream()
                        .map(light ->
                                new LightStateDto(
                                        light.getDirection().name(),
                                        light.getColor().name(),
                                        light.getSince()
                                )
                        )
                        .toList();

        return new IntersectionStateDto(
                intersection.getId(),
                intersection.isPaused(),
                lights
        );
    }

    /* =============================
       ENTITY → DTO
       ============================= */

    public static PhaseHistoryDto toDto(PhaseHistory history) {
        return new PhaseHistoryDto(
                history.getDirection(),
                history.getColor(),
                history.getChangedAt()
        );
    }

    public static List<PhaseHistoryDto> toHistoryDtos(List<PhaseHistory> history) {
        return history.stream()
                .map(IntersectionMapper::toDto)
                .toList();
    }
}
