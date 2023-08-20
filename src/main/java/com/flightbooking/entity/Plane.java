package com.flightbooking.entity;

import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Plane {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 36, updatable = false, unique = true)
    private UUID uuid;
    private String planeNum;
    private int size;
    @ManyToOne
    private Airport airport;
    @OneToOne
    private Flight flight;
    @OneToMany
    @CollectionTable(name = "flight_firstClass",
                     joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "firstClass")
    @ToString.Exclude
    private Set<Card> firstClass;
    @OneToMany
    @CollectionTable(name = "flight_economyClass",
                     joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "economyClass")
    @ToString.Exclude
    private Set<Card> economyClass;
    @OneToMany
    @CollectionTable(name = "flight_businessClass",
                     joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "businessClass")
    @ToString.Exclude
    private Set<Card> businessClass;

}
