package com.flightbooking.mapper;

import com.flightbooking.entity.Airport;
import com.flightbooking.web.dto.AirportDto;

import org.mapstruct.Mapper;

@Mapper
public interface AirportMapper {
    Airport map(AirportDto airportDto);

    AirportDto map(Airport airport);
}
