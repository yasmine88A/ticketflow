package com.ticketflow.eventservice.services;

import com.ticketflow.eventservice.entities.Event;
import com.ticketflow.eventservice.repositories.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Événement introuvable : " + id
                ));
    }

    public Event create(String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Le nom de l'événement est obligatoire"
            );
        }

        return repository.save(new Event(name.trim()));
    }
}