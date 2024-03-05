package com.reservationapp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id",unique = true)
    private Long busId;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "bus_type")
    private String busType;

    @Column(name = "departure_location")
    private String from;

    @Column(name = "destination_location")
    private String to;

   //@Temporal(TemporalType.DATE) @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "departure_date")
    private String fromDate;


    @Column(name = "return_date")
    private String toDate;

    @Column(name = "total_duration")
    private String totalDuration;

    @Column(name = "departure_time")
    private String fromTime;

    @Column(name = "return_time")
    private String toTime;

    @Column(name = "price")
    private double price;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "available_seats")
    private int availableSeats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    private Driver driver;
    // Getters and setters

    // Other methods or annotations specific to your requirements can be added here
}
