package com.example.hotel.services;

import com.example.hotel.dtos.RoomTypePriceDto;
import com.example.hotel.models.RoomType;
import com.example.hotel.models.RoomTypePrice;
import com.example.hotel.repositories.RoomTypePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypePriceService {
    @Autowired
    RoomTypePriceRepository roomTypePriceRepository;

    public RoomTypePrice setRoomPrice(RoomTypePriceDto roomTypePriceDto, Long userId){
        RoomTypePrice roomTypePrice = new RoomTypePrice();
        roomTypePrice.setRoomType(RoomType.valueOf(roomTypePriceDto.getRoomType()));
        roomTypePrice.setPrice(roomTypePriceDto.getPrice());
        return roomTypePriceRepository.save(roomTypePrice);
    }
}
