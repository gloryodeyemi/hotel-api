package com.example.hotel.controllers;

import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.Reservation;
import com.example.hotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> makeReservation(@RequestBody Reservation reservation) throws ErrorException {
        return ResponseEntity.ok(reservationService.makeReservation(reservation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(reservationService.getReservationByUserId(userId));
    }
}
