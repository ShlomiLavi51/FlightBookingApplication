package com.flightbooking.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightbooking.entity.Card;
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
public class CustomerDto {
    @JsonProperty("id")
    private UUID uuid;
    private String fullName;
    private String email;
    private String password;
    private SeatType seatType;
    @ToString.Exclude
    private Card card;
}
