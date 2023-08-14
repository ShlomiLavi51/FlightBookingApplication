package com.flightbooking.service;

import com.flightbooking.entity.Card;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Plane;
import com.flightbooking.entity.SeatType;
import com.flightbooking.mapper.CardMapper;
import com.flightbooking.mapper.FlightMapper;
import com.flightbooking.mapper.PlaneMapper;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.repository.CardRepository;
import com.flightbooking.repository.CustomerRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.PlaneRepository;
import com.flightbooking.service.ex.FlightAlreadyExistsException;
import com.flightbooking.service.ex.NotFoundException;
import com.flightbooking.web.dto.CardDto;
import com.flightbooking.web.dto.FlightDto;
import com.flightbooking.web.dto.ManagerDto;
import com.flightbooking.web.dto.PlaneDto;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    private final FlightMapper flightMapper;
    private final PlaneMapper planeMapper;
    private final CardMapper cardMapper;


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
        Optional<Plane> plane = planeRepository.findByUuid(dto.getPlane().getUuid());
        if (flightRepository.findByUuid(dto.getUuid()).isPresent()) {
            throw new FlightAlreadyExistsException("This Flight already exists in the "
                                                   + "system");
        } else if (plane.isEmpty()) {
            throw new NotFoundException("This Plane does not exist");
        } else if (dto.getCard().isEmpty()) {
            throw new NotFoundException("No flight tickets available");
        }
        List<CardDto> cards = dto.getCard()
                                 .stream()
                                 .map(cardDto -> createCard(cardDto,
                                                            dto.getPlane()
                                                               .getUuid(),
                                                            firstClass,
                                                            economyClass,
                                                            businessClass))
                                 .toList();
        dto.setCard(new HashSet<>(cards));
        flightRepository.save(flightMapper.map(dto));
        return dto;
    }

    @Override
    public CardDto createCard(
            CardDto dto, UUID uuid, double firstClass, double economyClass,
            double businessClass) {
        Optional<Plane> plane = planeRepository.findByUuid(uuid);
        Card card = cardMapper.map(dto);
        if (dto.getSeatType() == SeatType.FIRST_CLASS) {
            dto.setPrice(firstClass);
            plane.ifPresent(value -> value.setFirstClass(Collections.singleton(card)));
        } else if (dto.getSeatType() == SeatType.BUSiNESS_CLASS) {
            dto.setPrice(businessClass);
            plane.ifPresent(value -> value.setBusinessClass(Collections.singleton(card)));
        } else if (dto.getSeatType() == SeatType.ECONOMY_CLASS) {
            dto.setPrice(economyClass);
            plane.ifPresent(value -> value.setEconomyClass(Collections.singleton(card)));
        }

        planeRepository.save(plane.get());
        cardRepository.save(card);

        return cardMapper.map(card);
    }

    @Override
    public PlaneDto createPlane(PlaneDto planeDto) {
        if (planeRepository.findByUuid(planeDto.getUuid()).isEmpty()) {
            throw new NotFoundException("This Plane does not exist");
        }
        planeRepository.save(planeMapper.map(planeDto));
        return planeDto;
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
