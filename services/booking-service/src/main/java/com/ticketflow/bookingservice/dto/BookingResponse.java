package com.ticketflow.bookingservice.dto;

import com.ticketflow.bookingservice.entities.Booking;

public record BookingResponse(Long id, Long eventId) {

    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getEventId()
        );
    }
}