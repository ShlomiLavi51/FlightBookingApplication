package com.flightbooking.service;

import com.flightbooking.web.dto.CardDto;
import com.flightbooking.web.dto.FlightDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<CardDto> getCard(UUID uuid);

    List<FlightDto> getAllByDate(Date date);

    List<FlightDto> getAllByPrice(double price);

    void updateCustomer(UUID uuid);

    Optional<CardDto> purchased(UUID cardUuid, UUID customerUuid);
}
