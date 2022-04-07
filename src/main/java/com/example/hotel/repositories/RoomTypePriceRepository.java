package com.example.hotel.repositories;

import com.example.hotel.models.RoomType;
import com.example.hotel.models.RoomTypePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypePriceRepository extends JpaRepository<RoomTypePrice, Long> {
    Optional<RoomTypePrice> findByRoomType(RoomType roomType);
}
