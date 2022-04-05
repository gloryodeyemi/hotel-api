package com.example.hotel.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordDto {
    private String staffID;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
