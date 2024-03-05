package com.reservationapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id", unique = true)
    private Long driverId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "add_number")
    private String addNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "alternate_number")
    private String alternateNumber;

    @Column(name = "email_id")
    private String emailId;

    // Getters and setters, constructors, and other methods as needed
}
