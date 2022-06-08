package com.example.hotel.controllers;

import com.example.hotel.dtos.ReservationDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.HotelRoom;
import com.example.hotel.models.Reservation;
import com.example.hotel.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> makeReservation(@RequestBody ReservationDto reservationDto) throws ErrorException {
        log.info("reservationDto::{}", reservationDto);
        return ResponseEntity.ok(reservationService.makeReservation(reservationDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(reservationService.getReservationByUserId(userId));
    }

    @GetMapping("/checkout/{roomCode}")
    public ResponseEntity<HotelRoom> checkOut(@PathVariable String roomCode) throws ErrorException{
        return ResponseEntity.ok(reservationService.checkOut(roomCode));
    }
}
