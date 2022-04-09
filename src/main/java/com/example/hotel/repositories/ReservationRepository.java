package com.example.hotel.repositories;

import com.example.hotel.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    List<Reservation> findByUserIdAndActive(Long userId, Boolean active);
}
