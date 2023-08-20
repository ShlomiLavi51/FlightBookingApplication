package com.flightbooking.repository;

import com.flightbooking.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;



public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Manager findByUuid(UUID uuid);
    Optional<Manager> findByEmailAndPassword(String email, String password);

}
