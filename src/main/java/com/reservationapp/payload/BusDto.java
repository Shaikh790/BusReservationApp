package com.reservationapp.payload;

import com.reservationapp.entity.Driver;
import jakarta.persistence.Column;

import lombok.Data;


@Data
public class BusDto {
    private Long busId;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "bus_type")
    private String busType;

    @Column(name = "departure_location")
    private String from;

    @Column(name = "destination_location")
    private String to;

    //@Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private String fromDate;

    //@Temporal(TemporalType.DATE)
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
    private Driver driver;
}
