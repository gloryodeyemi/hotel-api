package com.example.hotel.services;

import com.example.hotel.dtos.RoomTypePriceDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.RoomType;
import com.example.hotel.models.RoomTypePrice;
import com.example.hotel.models.UserAccount;
import com.example.hotel.models.UserType;
import com.example.hotel.repositories.RoomTypePriceRepository;
import com.example.hotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypePriceService {
    @Autowired
    RoomTypePriceRepository roomTypePriceRepository;

    @Autowired
    UserRepository userRepository;

    public RoomTypePrice setRoomPrice(RoomTypePriceDto roomTypePriceDto, Long userId) throws ErrorException {
        userValidation(userId);
        // set room price if user is a staff
        RoomTypePrice roomTypePrice = new RoomTypePrice();
        roomTypePrice.setRoomType(RoomType.valueOf(roomTypePriceDto.getRoomType()));
        roomTypePrice.setPrice(roomTypePriceDto.getPrice());
        return roomTypePriceRepository.save(roomTypePrice);
    }

    public RoomTypePrice updateRoomPrice(RoomTypePriceDto roomTypePriceDto, Long userId) throws ErrorException {
        userValidation(userId);
        Optional<RoomTypePrice> roomTypePrice = roomTypePriceRepository.findByRoomType(RoomType.valueOf(roomTypePriceDto.getRoomType()));
        if (roomTypePrice.isEmpty()){
            throw new ErrorException("Room type not found!");
        }
        roomTypePrice.get().setPrice(roomTypePriceDto.getPrice());
        return roomTypePriceRepository.save(roomTypePrice.get());
    }

    public Double getRoomPrice(String roomType) throws ErrorException{
        Optional<RoomTypePrice> roomTypePrice = roomTypePriceRepository.findByRoomType(RoomType.valueOf(roomType));
        if (roomTypePrice.isEmpty()){
            throw new ErrorException("Room type not found!");
        }
        return roomTypePrice.get().getPrice();
    }

    public List<RoomTypePrice> getAllRoomPrice() {
        return roomTypePriceRepository.findAll();
    }

    public void userValidation(Long userId) throws ErrorException {
        //check if user exists
        Optional<UserAccount> user = userRepository.findUserById(userId);
        if (user.isEmpty()){
            throw new ErrorException("User not registered!");
        }
        //check if user is a customer
        if (UserType.valueOf("CUSTOMER").equals(user.get().getUserType())){
            throw new ErrorException("Action not allowed!");
        }
    }
}
