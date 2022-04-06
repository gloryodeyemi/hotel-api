package com.example.hotel.dtos;

import com.example.hotel.models.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String userType;
    private String password;
    private String confirmPassword;
}
