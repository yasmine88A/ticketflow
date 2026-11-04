package com.ticketflow.bookingservice.services;

import com.ticketflow.bookingservice.clients.EventClient;
import com.ticketflow.bookingservice.dto.EventResponse;
import com.ticketflow.bookingservice.entities.Booking;
import com.ticketflow.bookingservice.repositories.BookingRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {

    private final EventClient eventClient;
    private final BookingRepository repository;

    public BookingService(EventClient eventClient,
                          BookingRepository repository) {
        this.eventClient = eventClient;
        this.repository = repository;
    }

    public Booking create(Long eventId) {
        if (eventId == null || eventId <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Un identifiant d'événement positif est obligatoire"
            );
        }

        EventResponse event;

        try {
            event = eventClient.getById(eventId);
        } catch (FeignException.NotFound exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Événement introuvable : " + eventId,
                    exception
            );
        } catch (FeignException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Impossible de vérifier l'événement auprès d'Event Service",
                    exception
            );
        }

        if (event == null || !eventId.equals(event.id())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "Réponse invalide d'Event Service"
            );
        }

        return repository.save(new Booking(event.id()));
    }

    public List<Booking> getAll() {
        return repository.findAll();
    }
}