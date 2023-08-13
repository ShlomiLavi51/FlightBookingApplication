package com.flightbooking.repository;

import com.flightbooking.entity.Card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card,Long> {
    Optional<Card> findByUuid(UUID uuid);
    Card findBySeat(String seat);
}
