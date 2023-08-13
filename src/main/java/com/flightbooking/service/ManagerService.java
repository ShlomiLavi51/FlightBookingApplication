package com.flightbooking.service;

import com.flightbooking.entity.Card;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Plane;
import com.flightbooking.web.dto.FlightDto;
import com.flightbooking.web.dto.ManagerDto;

import java.util.Optional;
import java.util.UUID;

public interface ManagerService {
    Optional<Customer> getCustomerByUuid(UUID uuid);

    Optional<Card> getCardByUuid(UUID uuid);

    Optional<Flight> getFlightByUuid(UUID uuid);

    Optional<Plane> getPlaneByUuid(UUID uuid);

    FlightDto createFlight(
            FlightDto flightDto, double firstClass, double economyClass,
            double businessClass);

    void deleteFlight(UUID uuid);

    void deletePlane(UUID uuid);

    void deleteCustomer(UUID uuid);

    void updateManager(UUID uuid, ManagerDto dto);

    void updateFlight(
            UUID uuid, FlightDto dto);

}
