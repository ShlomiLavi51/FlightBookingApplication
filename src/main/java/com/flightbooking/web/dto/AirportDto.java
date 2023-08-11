package com.flightbooking.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Plane;

import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AirportDto {
    @JsonProperty("id")
    private UUID uuid;
    private String airportName;
    private Set<Flight> flights;
    private Set<Plane> planes;
}
