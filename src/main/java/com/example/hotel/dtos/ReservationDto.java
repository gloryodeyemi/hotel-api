package com.example.hotel.dtos;

import com.example.hotel.models.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationDto {
    private Long id;
    private Long userId;
    private Long roomId;
    private Long numberOfPersons;
    private String arrivalDate;
    private String departureDate;
    private PaymentStatus paymentStatus;
    private Boolean active;
}
