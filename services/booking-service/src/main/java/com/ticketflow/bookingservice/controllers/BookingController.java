package com.ticketflow.bookingservice.controllers;

import com.ticketflow.bookingservice.dto.BookingResponse;
import com.ticketflow.bookingservice.dto.CreateBookingRequest;
import com.ticketflow.bookingservice.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse create(
            @RequestBody CreateBookingRequest request) {
        return BookingResponse.from(
                service.create(request.eventId())
        );
    }

    @GetMapping
    public List<BookingResponse> getAll() {
        return service.getAll()
                .stream()
                .map(BookingResponse::from)
                .toList();
    }
}