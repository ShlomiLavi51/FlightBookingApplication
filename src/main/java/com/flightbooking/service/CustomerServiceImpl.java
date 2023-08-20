package com.flightbooking.service;

import com.flightbooking.entity.Card;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.SeatType;
import com.flightbooking.mapper.CardMapper;
import com.flightbooking.mapper.FlightMapper;
import com.flightbooking.repository.CardRepository;
import com.flightbooking.repository.CustomerRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.service.ex.NotFoundException;
import com.flightbooking.web.dto.CardDto;
import com.flightbooking.web.dto.CustomerDto;
import com.flightbooking.web.dto.FlightDto;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final FlightRepository flightRepository;
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final FlightMapper flightMapper;
    private final CardMapper cardMapper;


    @Override
    public CardDto getCard(UUID uuid) {
        return cardMapper.map(cardRepository.findByUuid(uuid)
                                            .orElseThrow(() -> new NotFoundException(
                                                    "Card "
                                                    + "with UUID "
                                                    + uuid
                                                    + " not found")));
    }

    @Override
    public List<FlightDto> getAllByDate(LocalDateTime date) {

        List<FlightDto> flightDto =
                flightRepository.findFlightsByDepartureTimeBefore(date)
                                                    .stream()
                                                    .map(flightMapper::map)
                                                    .toList();
        if (flightDto.isEmpty()) {
            throw new NotFoundException("No flights available on the selected date");
        }
        return flightDto;
    }

    @Override
    public List<FlightDto> getAllByPrice(double price, double price1) {
        List<FlightDto> flightDto = flightRepository.findFlightsByPriceRangeBetween(price,
                                                                                    price1)
                                                    .stream()
                                                    .map(flightMapper::map)
                                                    .toList();
        if (flightDto.isEmpty()) {
            throw new NotFoundException("No flights available on the selected date");
        }
        return flightDto;
    }

    @Override
    public void updateCustomer(CustomerDto dto) {
        Customer customer = customerRepository.findByUuid(dto.getUuid())
                                              .orElseThrow(() -> new NotFoundException(
                                                      "Customer with UUID "
                                                      + dto.getUuid()
                                                      + " not found"));
        customer.setEmail(dto.getEmail());
        customer.setFullName(dto.getFullName());
        customer.setPassword(dto.getPassword());

        customerRepository.save(customer);
    }

    @Override
    public CardDto purchased(UUID flightUuid, UUID customerUuid, SeatType seatType) {
        Customer customer = customerRepository.findByUuid(customerUuid)
                                              .orElseThrow(() -> new NotFoundException(
                                                      "Customer with UUID "
                                                      + customerUuid
                                                      + " not found"));
        Flight flight = flightRepository.findByUuid(flightUuid)
                                        .orElseThrow(() -> new NotFoundException(
                                                "Customer with UUID "
                                                + flightUuid
                                                + " not found"));

        Card card = flight.getCards()
                          .stream()
                          .filter(c -> c.getSeatType() == seatType
                                       && !c.isSeatTaken())
                          .findFirst()
                          .orElseThrow(() -> new NotFoundException("No available seat "
                                                                   + "for this type"
                                                                   + seatType));

        customer.add(card);
        card.setSeatTaken(true);
        card.setCustomer(customer);
        cardRepository.save(card);
        customerRepository.save(customer);

        return cardMapper.map(card);
    }

    @Override
    public Set<FlightDto> findFlightByDepartureTime(LocalDateTime departure) {
        Set<FlightDto> flightDto = flightRepository.findFlightsByDepartureTimeBefore(
                departure).stream().map(flightMapper::map).collect(Collectors.toSet());
        if (flightDto.isEmpty()) {
            throw new NotFoundException("No flights available for the given date.");
        }
        return flightDto;
    }

    @Override
    public Optional<CardDto> findBySeat(String seat) {

        return Optional.empty();
    }

}
