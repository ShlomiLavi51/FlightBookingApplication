package com.flightbooking.mapper;

import com.flightbooking.entity.Customer;
import com.flightbooking.web.dto.CustomerDto;

import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer map(CustomerDto customerDto);

    CustomerDto map(Customer customer);
}
