package com.flightbooking.repository;

import com.flightbooking.entity.Plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PlaneRepository extends JpaRepository<Plane,Long> {
    Optional<Plane> findByUuid(UUID uuid);

    Plane findPlaneBySize(int size);

    @Modifying
    @Query("delete from Plane c where c.uuid = ?1")
    void deleteByUuid(UUID uuid);
}
