package com.reservationapp.payload;

import javax.persistence.*;

public class UserRegistrationDto {

    private Long id;

    private String name;
    private String email;
    private String password;

    @Lob
    private byte[] profilePicture;
}
