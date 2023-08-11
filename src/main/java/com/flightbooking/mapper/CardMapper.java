package com.flightbooking.mapper;

import com.flightbooking.entity.Card;
import com.flightbooking.web.dto.CardDto;

import org.mapstruct.Mapper;

@Mapper
public interface CardMapper {
    Card map(CardDto cardDto);

    CardDto map(Card card);

}
