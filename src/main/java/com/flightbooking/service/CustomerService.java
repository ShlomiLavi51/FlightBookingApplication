package com.flightbooking.service;

import com.flightbooking.web.dto.CardDto;
import com.flightbooking.web.dto.CustomerDto;
import com.flightbooking.web.dto.FlightDto;
import com.flightbooking.web.dto.PlaneDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CustomerService {
    CardDto getCard(UUID uuid);

    List<FlightDto> getAllByDate(LocalDateTime date);

    List<FlightDto> getAllByPrice(double price,double price1);

    void updateCustomer(CustomerDto dto);

    Optional<CardDto> purchased(UUID cardUuid, UUID customerUuid);

    Set<PlaneDto> findFlightByDepartureTime(LocalDateTime departure);

    Optional<CardDto> findBySeat(String seat);

}
