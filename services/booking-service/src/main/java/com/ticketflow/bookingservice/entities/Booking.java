package com.ticketflow.bookingservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    protected Booking() {
    }

    public Booking(Long eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public Long getEventId() {
        return eventId;
    }
}