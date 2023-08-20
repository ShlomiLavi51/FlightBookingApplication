package com.flightbooking.entity;

import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class Customer {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 36, updatable = false, unique = true)
    private UUID uuid;
    private SeatType seatType;
    private String fullName;
    private String email;
    private String password;
    @ManyToMany
    @JoinColumn(name = "card_id")
    @ToString.Exclude
    private Set<Card> card;

    public void add(Card card) {
        this.card.add(card);
    }
}

