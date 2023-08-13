package com.flightbooking.repository;

import com.flightbooking.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUuid(UUID uuid);

    @Modifying
    @Query("delete from Plane c where c.uuid = ?1")
    void deleteByUuid(UUID uuid);

}
