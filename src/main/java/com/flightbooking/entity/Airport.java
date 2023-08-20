package com.flightbooking.entity;

import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Airport {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 36, updatable = false, unique = true)
    private UUID uuid;
    private String airportName;
    @OneToMany
    @ToString.Exclude
    private Set<Flight> flights;
    @OneToMany
    @ToString.Exclude
    private Set<Plane> planes;
}
