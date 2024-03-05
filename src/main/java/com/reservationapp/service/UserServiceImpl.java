package com.reservationapp.service;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.payload.UserRegistrationDto;
import com.reservationapp.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepositoty userRepositoty;

    public UserRegistrationDto createUser(UserRegistration userRegistration){
        userRepositoty.save(userRegistration);
        return null;
    }

    public UserRegistration getUserById(Long id) {
       return userRepositoty.findById(id).get();
    }
}
