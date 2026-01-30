package com.natwest.trafficlightsystem.controller;

import com.natwest.trafficlightsystem.dto.IntersectionMapper;
import com.natwest.trafficlightsystem.dto.IntersectionStateDto;
import com.natwest.trafficlightsystem.service.IntersectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intersections")
@Tag(name = "Traffic Light Controller")
public class IntersectionController {

    private final IntersectionService service;

    public IntersectionController(IntersectionService service) {
        this.service = service;
    }

    @Operation(summary = "Get current intersection state")
    @GetMapping("/{id}/state")
    public IntersectionStateDto getState(@PathVariable String id) {
        return IntersectionMapper.toDto(service.getIntersection(id));
    }

    @PostMapping("/{id}/advance")
    public void advance(@PathVariable String id) {
        service.advancePhase(id);
    }

}
