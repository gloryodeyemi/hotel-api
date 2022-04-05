package com.example.hotel.repositories;

import com.example.hotel.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findStaffByStaffId(String staffId);
    Boolean existsByEmailAddress(String emailAddress);
}
