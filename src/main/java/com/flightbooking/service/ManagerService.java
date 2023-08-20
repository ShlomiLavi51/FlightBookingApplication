package com.flightbooking.service;

import com.flightbooking.entity.Card;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Manager;
import com.flightbooking.entity.Plane;
import com.flightbooking.web.dto.CardDto;
import com.flightbooking.web.dto.FlightDto;
import com.flightbooking.web.dto.PlaneDto;

import java.util.UUID;

public interface ManagerService {
    Customer getCustomerByUuid(UUID uuid);

    Card getCardByUuid(UUID uuid);

    Flight getFlightByUuid(UUID uuid);

    Plane getPlaneByUuid(UUID uuid);

    FlightDto createFlight(
            FlightDto flightDto, double firstClass, double economyClass,
            double businessClass);

    CardDto createCard(CardDto dto, UUID uuid , double firstClass, double economyClass, double businessClass);

    PlaneDto createPlane(PlaneDto planeDto);

    void deleteFlight(UUID uuid);

    void deletePlane(UUID uuid);

    void deleteCustomer(UUID uuid);

    void updateManager( Manager manager);

    Plane findPlaneBySize(int size);

    void updateFlightTime(FlightDto dto);

    void updateFlight(FlightDto dto);

}
