package com.flightbooking.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 36, updatable = false, unique = true)
    private UUID uuid;
    @Column(nullable = false)
    private String flightNumber;
    private double priceRange;
    @Column(nullable = false)
    private String destination;
    @ManyToOne
    private Airport airport;
    @Column(nullable = false)
    private LocalDateTime departureTime;
    @Column(nullable = false)
    private LocalDateTime landingTime;
    @Column(nullable = false)
    private Date flightTime;
    @OneToMany
    private Set<Card> cards;
    @OneToOne
    private Plane plane;
}
