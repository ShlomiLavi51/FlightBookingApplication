package com.flightbooking.repository;

import com.flightbooking.entity.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByUuid(UUID uuid);

    @Modifying
    @Query("delete from Flight c where c.departureTime<current_timestamp ")
    void deleteAllByEndDateAfter();

    List<Flight> findFlightsByDepartureTimeBefore(LocalDateTime departure);

    @Modifying
    @Query("delete from Flight c where c.uuid = ?1")
    void deleteByUuid(UUID uuid);

    List<Flight> findFlightsByPriceRangeBetween(double price, double price1);
}
