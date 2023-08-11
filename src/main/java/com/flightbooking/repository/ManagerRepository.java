package com.flightbooking.repository;

import com.flightbooking.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Manager findByUuid(UUID uuid);
}
