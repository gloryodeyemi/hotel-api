package com.example.hotel.controllers;

import com.example.hotel.dtos.RoomTypePriceDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.RoomTypePrice;
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

    @PostMapping("/set-price/{userId}")
    public ResponseEntity<RoomTypePrice> setRoomPrice(@RequestBody RoomTypePriceDto roomTypePriceDto, @PathVariable Long userId) throws ErrorException {
        return ResponseEntity.ok(roomTypePriceService.setRoomPrice(roomTypePriceDto, userId));
    }

    @PatchMapping("/set-price/update/{userId}")
    public ResponseEntity<RoomTypePrice> updateRoomPrice(@RequestBody RoomTypePriceDto roomTypePriceDto, @PathVariable Long userId) throws ErrorException {
        return ResponseEntity.ok(roomTypePriceService.updateRoomPrice(roomTypePriceDto, userId));
    }

    @GetMapping("/room-price/{roomType}")
    public ResponseEntity<RoomTypePrice> getRoomPrice(@PathVariable String roomType) throws ErrorException {
        return ResponseEntity.ok(roomTypePriceService.getRoomPrice(roomType));
    }

    @GetMapping("/room-price-all")
        public ResponseEntity<List<RoomTypePrice>> getAllRoomPrice(){
        return ResponseEntity.ok(roomTypePriceService.getAllRoomPrice());
        }
}
