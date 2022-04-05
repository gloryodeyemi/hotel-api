package com.example.hotel.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Staff {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String staffId;
    private String staffPassword;

    @Override
    public final String toString(){
        return this.firstName + "-" + this.id;
    }

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private Timestamp dateUpdated;
}
