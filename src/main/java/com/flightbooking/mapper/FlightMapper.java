package com.flightbooking.mapper;

import com.flightbooking.entity.Flight;
import com.flightbooking.web.dto.FlightDto;

import org.mapstruct.Mapper;

@Mapper
public interface FlightMapper {
    Flight map(FlightDto flightDto);

    FlightDto map(Flight flight);
}
