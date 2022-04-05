package com.example.hotel.controllers;

import com.example.hotel.dtos.StaffDto;
import com.example.hotel.exceptions.StaffAlreadyExistException;
import com.example.hotel.models.Staff;
import com.example.hotel.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(@RequestBody StaffDto staffDto) throws StaffAlreadyExistException {
        return ResponseEntity.ok(staffService.createStaff(staffDto));
    }
}
