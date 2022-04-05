package com.example.hotel.services;

import com.example.hotel.dtos.StaffDto;
import com.example.hotel.exceptions.StaffAlreadyExistException;
import com.example.hotel.models.Staff;
import com.example.hotel.repositories.StaffRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


}
