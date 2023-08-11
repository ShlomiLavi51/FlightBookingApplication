package com.flightbooking.repository;

import com.flightbooking.entity.Plane;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaneRepository extends JpaRepository<Plane,Long> {
    Plane findByUuid(UUID uuid);

    Plane findPlaneBySize(int size);
}
