package com.flightbooking.web;


import com.flightbooking.entity.UserType;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientSession {
    private final UUID uuid;
    private long lastAccessedMillis;
    private final UserType userType;

    public static ClientSession of(UUID uuid,UserType userType) {
        return new ClientSession(uuid, System.currentTimeMillis(),userType);
    }

    public void updateLastAccessedMillis() {
        lastAccessedMillis = System.currentTimeMillis();
    }
}
