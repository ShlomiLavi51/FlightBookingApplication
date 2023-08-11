package com.flightbooking.mapper;

import com.flightbooking.entity.Plane;
import com.flightbooking.web.dto.PlaneDto;

import org.mapstruct.Mapper;

@Mapper
public interface PlaneMapper {
    Plane map(PlaneDto planeDto);

    PlaneDto map(Plane plane);
}
