package com.flightbooking.service.login;


import com.flightbooking.LoginResponse;
import com.flightbooking.entity.Customer;
import com.flightbooking.entity.Manager;
import com.flightbooking.entity.UserType;
import com.flightbooking.repository.CustomerRepository;
import com.flightbooking.repository.ManagerRepository;
import com.flightbooking.service.ex.InvalidLoginException;
import com.flightbooking.service.ex.UnauthorizedException;
import com.flightbooking.web.ClientSession;
import com.flightbooking.web.controller.login.UserCredentials;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private static final int TOKEN_LENGTH = 8;
    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;

    @Override
    public ClientSession createSession(UserCredentials userCredentials) {
        if (userCredentials.getClientType() == UserType.CUSTOMER) {
            Optional<Customer> customer = customerRepository.findByEmailAndPassword(userCredentials.getEmail(),
                                                                                    userCredentials.getPassword());
            return ClientSession.of(customer
                                            .orElseThrow(InvalidLoginException::new)
                                            .getUuid(), UserType.CUSTOMER);
        } else if (userCredentials.getClientType() == UserType.MANAGER) {
            Optional<Manager> manager =
                    managerRepository.findByEmailAndPassword(userCredentials.getEmail(),
                                                                                 userCredentials.getPassword());
            return ClientSession.of(manager
                                            .orElseThrow(InvalidLoginException::new)
                                            .getUuid(), UserType.MANAGER);
        } else {
            throw new UnauthorizedException("Not Unauthorized!");
        }
    }

    @Override
    public String generateToken() {

    return     UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .substring(0, TOKEN_LENGTH);
    }

    public static LoginResponse ofSuccess(String token) {
        return new LoginResponse(true, "", System.currentTimeMillis(), token);
    }

}
