package com.flightbooking.repository;

import com.flightbooking.entity.Airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    Airport findByUuid(UUID uuid);

}
