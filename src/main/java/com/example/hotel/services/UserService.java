package com.example.hotel.services;

import com.example.hotel.dtos.StaffDto;
import com.example.hotel.dtos.UserDto;
import com.example.hotel.exceptions.ErrorException;
import com.example.hotel.exceptions.StaffAlreadyExistException;
import com.example.hotel.models.Staff;
import com.example.hotel.models.User;
import com.example.hotel.models.UserType;
import com.example.hotel.repositories.StaffRepository;
import com.example.hotel.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) throws ErrorException {
        // checking to see if user already exists
        if (userRepository.existsByEmailAddress(userDto.getEmailAddress())) {
            throw new ErrorException("Email address already exists!");
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setUserType(UserType.valueOf(userDto.getUserType()));

        if (userDto.getUserType().equals("CUSTOMER")){
            String password = passwordEncoder.encode(userDto.getPassword());
            if (passwordEncoder.matches(userDto.getConfirmPassword(), password)){
                user.setPassword(password);
                return userRepository.save(user);
            }
            throw new ErrorException("Password does not match!");
        } else if (userDto.getUserType().equals("STAFF")){
            User staffUser = userRepository.save(user);
            staffUser.setStaffId(staffUser.toString());
            String thePassword = passwordEncoder.encode(staffUser.getStaffId());
            staffUser.setPassword(thePassword);
            return userRepository.save(staffUser);
        }
        throw new ErrorException("No user type: " + userDto.getUserType() + "! Enter a valid user type.");
    }
}
