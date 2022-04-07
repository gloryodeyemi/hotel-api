package com.example.hotel.controllers;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.dtos.RoomTypePriceDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.HotelRoom;
import com.example.hotel.models.RoomTypePrice;
import com.example.hotel.services.RoomService;
import com.example.hotel.services.RoomTypePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {
    @Autowired
    RoomTypePriceService roomTypePriceService;

    @Autowired
    RoomService roomService;

    @PostMapping("/set-price/{userId}")
    public ResponseEntity<RoomTypePrice> setRoomPrice(@RequestBody RoomTypePriceDto roomTypePriceDto, @PathVariable Long userId) throws ErrorException {
        return ResponseEntity.ok(roomTypePriceService.setRoomPrice(roomTypePriceDto, userId));
    }

    @PatchMapping("/set-price/update/{userId}")
    public ResponseEntity<RoomTypePrice> updateRoomPrice(@RequestBody RoomTypePriceDto roomTypePriceDto, @PathVariable Long userId) throws ErrorException {
        return ResponseEntity.ok(roomTypePriceService.updateRoomPrice(roomTypePriceDto, userId));
    }

    @GetMapping("/room-price/{roomType}")
    public ResponseEntity<Double> getRoomPrice(@PathVariable String roomType) throws ErrorException {
        return ResponseEntity.ok(roomTypePriceService.getRoomPrice(roomType));
    }

    @GetMapping("/room-price-all")
    public ResponseEntity<List<RoomTypePrice>> getAllRoomPrice(){
        return ResponseEntity.ok(roomTypePriceService.getAllRoomPrice());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<HotelRoom> addRoom(@RequestBody RoomDto roomDto, @PathVariable Long userId) throws ErrorException {
        return ResponseEntity.ok(roomService.addRoom(roomDto, userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelRoom>> getAllRooms(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{roomCode}")
    public ResponseEntity<HotelRoom> getRoom(@PathVariable String roomCode) throws ErrorException {
        return ResponseEntity.ok(roomService.getRoom(roomCode));
    }
}
