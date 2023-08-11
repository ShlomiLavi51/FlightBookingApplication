package com.flightbooking.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ManagerDto {
    @JsonProperty("id")
    private UUID uuid;
    private String name;
    private String email;
}
