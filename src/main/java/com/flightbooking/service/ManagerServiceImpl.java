package com.flightbooking.service;

import com.flightbooking.entity.Card;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Manager;
import com.flightbooking.entity.Plane;
import com.flightbooking.mapper.CardMapper;
import com.flightbooking.mapper.FlightMapper;
import com.flightbooking.mapper.PlaneMapper;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.repository.CardRepository;
import com.flightbooking.repository.CustomerRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.ManagerRepository;
import com.flightbooking.repository.PlaneRepository;
import com.flightbooking.service.ex.FlightAlreadyExistsException;
import com.flightbooking.service.ex.NotFoundException;
import com.flightbooking.web.dto.CardDto;
import com.flightbooking.web.dto.FlightDto;
import com.flightbooking.web.dto.PlaneDto;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final FlightRepository flightRepository;
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final FlightMapper flightMapper;
    private final PlaneMapper planeMapper;
    private final CardMapper cardMapper;


    @Override
    public Customer getCustomerByUuid(UUID uuid) {
        return customerRepository.findByUuid(uuid)
                                 .orElseThrow(() -> new NotFoundException("Customer with UUID " + uuid + " not found"));
    }

    @Override
    public Card getCardByUuid(UUID uuid) {
        return cardRepository.findByUuid(uuid)
                                 .orElseThrow(() -> new NotFoundException("Customer with UUID " + uuid + " not found"));
    }

    @Override
    public Flight getFlightByUuid(UUID uuid) {
        return flightRepository.findByUuid(uuid)
                                 .orElseThrow(() -> new NotFoundException("Customer with UUID " + uuid + " not found"));
    }

    @Override
    public Plane getPlaneByUuid(UUID uuid) {
        return planeRepository.findByUuid(uuid)
                                 .orElseThrow(() -> new NotFoundException("Customer with UUID " + uuid + " not found"));
    }

    @Override
    public FlightDto createFlight(
            FlightDto dto, double firstClass, double economyClass, double businessClass) {
        planeRepository.findByUuid(dto.getPlane().getUuid())
                       .orElseThrow(() -> new NotFoundException("Plane with UUID "
                                                                + dto.getPlane().getUuid()
                                                                + " not found"));

        if (flightRepository.findByUuid(dto.getUuid()).isPresent()) {
            throw new FlightAlreadyExistsException(
                    "This Flight already exists in the system");
        } else if (dto.getCards().isEmpty()) {
            throw new NotFoundException("No flight tickets available");
        }

        List<CardDto> cards = dto.getCards()
                                 .stream()
                                 .map(cardDto -> createCard(cardDto,
                                                            dto.getPlane()
                                                               .getUuid(),
                                                            firstClass,
                                                            economyClass,
                                                            businessClass))
                                 .toList();
        dto.setCards(new HashSet<>(cards));
        flightRepository.save(flightMapper.map(dto));
        return dto;
    }

    @Override
    public CardDto createCard(
            CardDto dto, UUID uuid, double firstClass, double economyClass,
            double businessClass) {
        Plane plane = planeRepository.findByUuid(uuid)
                                     .orElseThrow(() -> new NotFoundException(
                                             "Plane with UUID " + uuid + " not found"));

        Card card = cardMapper.map(dto);

        switch (dto.getSeatType()) {
            case FIRST_CLASS -> {
                dto.setPrice(firstClass);
                plane.setFirstClass(Collections.singleton(cardMapper.map(dto)));
            }
            case BUSINESS_CLASS -> {
                dto.setPrice(businessClass);
                plane.setBusinessClass(Collections.singleton(cardMapper.map(dto)));
            }
            case ECONOMY_CLASS -> {
                dto.setPrice(economyClass);
                plane.setEconomyClass(Collections.singleton(cardMapper.map(dto)));
            }
        }

        planeRepository.save(plane);
        cardRepository.save(card);
        return cardMapper.map(card);
    }

    @Override
    public PlaneDto createPlane(PlaneDto planeDto) {
        planeRepository.findByUuid(planeDto.getUuid())
                       .orElseThrow(() -> new NotFoundException("This dto  is empty"));

        planeRepository.save(planeMapper.map(planeDto));
        return planeDto;
    }

    @Override
    public void deleteFlight(UUID uuid) {
        flightRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(
                "Flight with UUID " + uuid + " not fount"));
    }

    @Override
    public void deletePlane(UUID uuid) {
        planeRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(
                "Plane with UUID " + uuid + " not fount"));

        planeRepository.deleteByUuid(uuid);
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        customerRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(
                "Customer with UUID " + uuid + " not found"));

        customerRepository.deleteByUuid(uuid);
    }

    @Override
    public void updateManager(Manager manager) {
        manager.setEmail(manager.getEmail());
        manager.setName(manager.getName());
        manager.setPassword(manager.getPassword());
        managerRepository.save(manager);

    }

    @Override
    public Plane findPlaneBySize(int size) {
        PlaneDto planeDto = planeRepository.findPlaneBySize(size)
                                           .map(planeMapper::map)
                                           .orElseThrow(() -> new NotFoundException(
                                                   "Plane of size "
                                                   + size
                                                   + " does not exist"));

        return planeMapper.map(planeDto);
    }

    @Override
    public void updateFlightTime(FlightDto dto) {
        Flight flight;
        if (flightRepository.findByUuid(dto.getUuid()).isEmpty()) {
            throw new FlightAlreadyExistsException("The flight does not exist");
        } else {
            flight = flightRepository.findByUuid(dto.getUuid()).get();
        }
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setLandingTime(dto.getLandingTime());
        flight.setFlightTime(dto.getFlightTime());
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(FlightDto dto) {
        Flight flight;
        if (flightRepository.findByUuid(dto.getUuid()).isEmpty()) {
            throw new FlightAlreadyExistsException("The flight does not exist");
        } else {
            flight = flightRepository.findByUuid(dto.getUuid()).get();
        }
        updateFlightTime(dto);
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setDestination(dto.getDestination());
        flight.setPlane(dto.getPlane());

        flightRepository.save(flight);
    }
}
