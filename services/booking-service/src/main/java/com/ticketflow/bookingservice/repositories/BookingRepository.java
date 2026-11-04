package com.ticketflow.bookingservice.repositories;

import com.ticketflow.bookingservice.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}