package com.example.hotel.controllers;

import com.example.hotel.dtos.UserDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.UserAccount;
import com.example.hotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserAccount> createUser(@RequestBody UserDto userDto) throws ErrorException {
        return ResponseEntity.ok(userService.createUser(userDto));
    }
}
