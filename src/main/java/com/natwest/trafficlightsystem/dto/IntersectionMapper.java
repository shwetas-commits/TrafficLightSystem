package com.natwest.trafficlightsystem.dto;

import com.natwest.trafficlightsystem.domain.intersection.Intersection;
import com.natwest.trafficlightsystem.domain.intersection.LightColor;
import com.natwest.trafficlightsystem.domain.intersection.TrafficPhase;

import java.time.Instant;
import java.util.List;

//converts between domain models and API DTOs
public class IntersectionMapper {

    public static IntersectionStateDto toDto(Intersection intersection) {
        TrafficPhase phase = intersection.getCurrentPhase();

        List<LightStateDto> lights =
                intersection.getAllDirections().stream()
                        .map(direction -> new LightStateDto(
                                direction.name(),
                                phase.getGreenDirections().contains(direction)
                                        ? LightColor.GREEN.name()
                                        : LightColor.RED.name(),
                                Instant.now()
                        ))
                        .toList();

        return new IntersectionStateDto(
                intersection.getId(),
                intersection.isPaused(),
                lights
        );
    }
}
