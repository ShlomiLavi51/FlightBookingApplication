package com.flightbooking.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightbooking.entity.Plane;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class FlightDto {
    @JsonProperty("id")
    private UUID uuid;
    private String flightNumber;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime landingTime;
    private Date flightTime;
    private Plane plane;
}
