package com.example.hotel.services;

import com.example.hotel.dtos.RoomDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.models.HotelRoom;
import com.example.hotel.models.RoomStatus;
import com.example.hotel.models.RoomType;
import com.example.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomTypePriceService roomTypePriceService;

    public HotelRoom addRoom(RoomDto roomDto, Long userId) throws ErrorException {
        roomTypePriceService.userValidation(userId);
        HotelRoom newRoom = new HotelRoom();
        newRoom.setRoomType(RoomType.valueOf(roomDto.getRoomType()));
        newRoom.setPrice(roomTypePriceService.getRoomPrice(roomDto.getRoomType()));
        newRoom.setRoomStatus(RoomStatus.AVAILABLE);
        HotelRoom room = roomRepository.save(newRoom);
        room.setRoomCode(room.toString());
        return roomRepository.save(room);
    }

    public List<HotelRoom> getAllRooms(){
        return roomRepository.findAll();
    }

    public HotelRoom getRoom(String roomCode) throws ErrorException{
        Optional<HotelRoom> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()){
            return room.get();
        }
        throw new ErrorException("Room not found!");
    }

    public List<HotelRoom> getRoomsByStatus(RoomStatus roomStatus) {
        Optional<List<HotelRoom>> rooms = roomRepository.findAllByRoomStatus(roomStatus);
        return rooms.orElse(null);
    }

    public List<HotelRoom> getRoomsByType(RoomType roomType) {
        Optional<List<HotelRoom>> rooms = roomRepository.findAllByRoomType(roomType);
        return rooms.orElse(null);
    }
}
