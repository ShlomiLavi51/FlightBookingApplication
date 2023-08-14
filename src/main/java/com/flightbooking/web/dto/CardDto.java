package com.flightbooking.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.SeatType;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CardDto {
    @JsonProperty("id")
    private UUID uuid;
    private String gate;
    private String seat;
    private SeatType seatType;
    private double price;
    private Flight flight;
    private Customer customer;
}
