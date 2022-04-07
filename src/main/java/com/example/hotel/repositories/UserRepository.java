package com.example.hotel.repositories;

import com.example.hotel.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findUserById(Long userId);
    Boolean existsByEmailAddress(String emailAddress);
}
