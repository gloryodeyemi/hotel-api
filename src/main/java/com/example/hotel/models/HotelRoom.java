package com.example.hotel.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class HotelRoom {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomCode;
    private RoomType roomType;
    private Double price;
    private RoomStatus roomStatus;

    @Override
    public final String toString(){
        return this.roomType + "-" + this.id;
    }

    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateUpdated;
}
