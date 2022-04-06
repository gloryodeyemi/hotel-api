package com.example.hotel.repositories;

import com.example.hotel.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long userId);
    Boolean existsByEmailAddress(String emailAddress);
}
