package com.flightbooking.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightbooking.entity.Airport;

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
public class PlaneDto {
    @JsonProperty("id")
    private UUID uuid;
    private String planeNum;
    private Airport airport;
    private Set<String> seats;
}
