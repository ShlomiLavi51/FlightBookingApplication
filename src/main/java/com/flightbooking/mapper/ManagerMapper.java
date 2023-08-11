package com.flightbooking.mapper;

import com.flightbooking.entity.Manager;
import com.flightbooking.web.dto.ManagerDto;

import org.mapstruct.Mapper;

@Mapper
public interface ManagerMapper {
    Manager map(ManagerDto managerDto);

    ManagerDto map(Manager manager);
}
