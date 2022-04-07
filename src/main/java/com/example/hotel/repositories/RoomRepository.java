package com.example.hotel.repositories;

import com.example.hotel.models.HotelRoom;
import com.example.hotel.models.RoomStatus;
import com.example.hotel.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<HotelRoom, Long> {
    Optional<List<HotelRoom>> findAllByRoomType(RoomType roomType);
    Optional<List<HotelRoom>> findAllByRoomStatus(RoomStatus roomStatus);
    Optional<HotelRoom> findByRoomCode(String roomCode);
}
