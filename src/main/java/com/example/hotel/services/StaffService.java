package com.example.hotel.services;

import com.example.hotel.dtos.PasswordDto;
import com.example.hotel.dtos.StaffDto;
import com.example.hotel.exceptions.StaffAlreadyExistException;
import com.example.hotel.models.Staff;
import com.example.hotel.repositories.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Staff createStaff(StaffDto staffDto) throws StaffAlreadyExistException {
        // checking to see if user already exists
        if (staffRepository.existsByEmailAddress(staffDto.getEmailAddress())) {
            throw new StaffAlreadyExistException("Email address already exists!");
        }
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffDto, staff);
//        Staff theStaff = staffRepository.save(staff);
        Staff theStaff = staffRepository.save(staff);
        theStaff.setStaffId(theStaff.toString());
        String thePassword = passwordEncoder.encode(theStaff.getStaffId());
        theStaff.setStaffPassword(thePassword);
        return staffRepository.save(theStaff);
    }

    public Staff changeStaffPassword(PasswordDto passwordDto, Long id) throws StaffAlreadyExistException{
        Optional<Staff> staff = staffRepository.findStaffByStaffId(passwordDto.getStaffID());
        if (staff.isEmpty()){
            throw new StaffAlreadyExistException("Staff not found!");
        }
        if (!(staff.get().getId().equals(id))){
            throw new StaffAlreadyExistException("Action not allowed!");
        }
        if (passwordEncoder.matches(passwordDto.getOldPassword(), staff.get().getStaffPassword())){
            if (passwordEncoder.matches(passwordDto.getNewPassword(), staff.get().getStaffPassword())){
                throw new StaffAlreadyExistException("New password cannot be the same as old password!");
            }
            String newPassword = passwordEncoder.encode(passwordDto.getNewPassword());
            if (passwordEncoder.matches(passwordDto.getConfirmNewPassword(), newPassword)){
                staff.get().setStaffPassword(newPassword);
                return staffRepository.save(staff.get());
            }
            throw new StaffAlreadyExistException("Password does not match!");
        }
        throw new StaffAlreadyExistException("Old password is wrong!");
    }


}
