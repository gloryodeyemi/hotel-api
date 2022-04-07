package com.example.hotel.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class RoomTypePrice {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoomType roomType;
    private Double price;

    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateUpdated;
}
