package com.example.hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reservation {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private RoomType roomType;
    private Long numberOfPersons;
    private Long numberOfRooms;
    private Long numberOfNights;
    private Double totalAmount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDate;

    private PaymentStatus paymentStatus;
    private String message;

    @OneToMany
    @JsonIgnoreProperties({"id", "roomType", "price", "roomStatus", "dateCreated", "dateUpdated"})
    private List<HotelRoom> rooms;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateUpdated;


}
