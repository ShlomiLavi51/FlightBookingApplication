package com.flightbooking.web;


import com.flightbooking.entity.ClientType;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientSession {
    private final UUID uuid;
    private long lastAccessedMillis;
    private final ClientType clientType;

    public static ClientSession of(UUID uuid,ClientType clientType) {
        return new ClientSession(uuid, System.currentTimeMillis(),clientType);
    }

    public void updateLastAccessedMillis() {
        lastAccessedMillis = System.currentTimeMillis();
    }
}
