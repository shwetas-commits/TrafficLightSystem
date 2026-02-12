package com.natwest.trafficlightsystem.controller;

import com.natwest.trafficlightsystem.dto.IntersectionStateDto;
import com.natwest.trafficlightsystem.dto.PhaseHistoryDto;
import com.natwest.trafficlightsystem.dto.SequenceRequestDto;
import com.natwest.trafficlightsystem.mapper.IntersectionMapper;
import com.natwest.trafficlightsystem.service.IntersectionService;
import jakarta.validation.Valid;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intersections/{id}")
public class IntersectionController {

    private final IntersectionService service;

    public IntersectionController(IntersectionService service) {
        this.service = service;
    }

    @GetMapping("/state")
    public IntersectionStateDto state(@PathVariable String id) {
        return service.getState(id);
    }

    @PostMapping
    public void create(@PathVariable String id) {
        service.createIntersection(id);
    }

    @PostMapping("/start")
    public void start(@PathVariable String id) {
        service.start(id);
    }

    @PostMapping("/pause")
    public void pause(@PathVariable String id) {
        service.pause(id);
    }

    @PostMapping("/resume")
    public void resume(@PathVariable String id) {
        service.resume(id);
    }

    @PostMapping("/sequence")
    public void updateSequence(
            @PathVariable String id,
            @RequestBody @Valid SequenceRequestDto request
    ) {
        service.updateSequence(id, IntersectionMapper.toDomainPhases(request));
    }


/*
    @GetMapping("/history")
    public List<PhaseHistoryDto> history(@PathVariable String id) {
        return service.history(id);
    }
*/

    @GetMapping("/history")
    public List<PhaseHistoryDto> history(@PathVariable String id) {
        return IntersectionMapper.toHistoryDtos(
                service.getHistory(id)
        );
    }



    @Scheduled(fixedRate = 5000)
    void tick() {
        service.advancePhase("I1");
    }

}
