package com.flightbooking.repository;

import com.flightbooking.entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    Flight findByUuid(UUID uuid);

    void deleteFlightByDepartureTimeAfter(LocalDateTime departure);

    Flight findFlightByDepartureTime(LocalDateTime departure);

}
