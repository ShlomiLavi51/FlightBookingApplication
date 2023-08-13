package com.flightbooking.service;

import com.flightbooking.entity.Card;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Plane;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.repository.CardRepository;
import com.flightbooking.repository.CustomerRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.PlaneRepository;
import com.flightbooking.service.ex.FlightAlreadyExistsException;
import com.flightbooking.service.ex.NotFoundException;
import com.flightbooking.web.dto.FlightDto;
import com.flightbooking.web.dto.ManagerDto;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final FlightRepository flightRepository;
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;

    @Override
    public Optional<Customer> getCustomerByUuid(UUID uuid) {
        Optional<Customer> customer = customerRepository.findByUuid(uuid);
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer does not exist");
        }
        return customer;
    }

    @Override
    public Optional<Card> getCardByUuid(UUID uuid) {
        Optional<Card> card = cardRepository.findByUuid(uuid);
        if (card.isEmpty()) {
            throw new NotFoundException("Card does not exist");
        }
        return card;
    }

    @Override
    public Optional<Flight> getFlightByUuid(UUID uuid) {
        Optional<Flight> flight = flightRepository.findByUuid(uuid);
        if (flight.isEmpty()) {
            throw new NotFoundException("Flight does not exist");
        }
        return flight;
    }

    @Override
    public Optional<Plane> getPlaneByUuid(UUID uuid) {
        Optional<Plane> plane = planeRepository.findByUuid(uuid);
        if (plane.isEmpty()) {
            throw new NotFoundException("Plane does not exist");
        }
        return plane;
    }

    @Override
    public FlightDto createFlight(
            FlightDto dto, double firstClass, double economyClass, double businessClass) {
        UUID uuidPlane = dto.getPlane().getUuid();
        if (flightRepository.findByUuid(dto.getUuid()).isPresent()) {
            throw new FlightAlreadyExistsException("This Flight already exists in the "
                                                   + "system");
        }
        if (planeRepository.findByUuid(uuidPlane).isEmpty()) {
            throw new NotFoundException("This Plane does not exist");
        }
    }

    @Override
    public void deleteFlight(UUID uuid) {
        if (flightRepository.findByUuid(uuid).isPresent()) {
            flightRepository.deleteByUuid(uuid);
        } else {
            throw new NotFoundException("The Flight does not exist");
        }
    }

    @Override
    public void deletePlane(UUID uuid) {
        if (planeRepository.findByUuid(uuid).isPresent()) {
            planeRepository.deleteByUuid(uuid);
        } else {
            throw new NotFoundException("The Plane does not exist");
        }
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        if (customerRepository.findByUuid(uuid).isPresent()) {
            customerRepository.deleteByUuid(uuid);
        } else {
            throw new NotFoundException("The Plane does not exist");
        }
    }

    @Override
    public void updateManager(UUID uuid, ManagerDto dto) {

    }

    @Override
    public void updateFlight(UUID uuid, FlightDto dto) {


    }
}
