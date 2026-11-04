package com.ticketflow.eventservice.controllers;

import com.ticketflow.eventservice.dto.CreateEventRequest;
import com.ticketflow.eventservice.dto.EventResponse;
import com.ticketflow.eventservice.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public EventResponse get(@PathVariable("id") Long id) {
        return EventResponse.from(service.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse create(@RequestBody CreateEventRequest request) {
        return EventResponse.from(service.create(request.name()));
    }
}