package com.reservationapp.payload;

import javax.persistence.*;


import lombok.Data;

@Data
public class SearchlistOfBusesDto  {
    private Long busId;


    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;

    private Long routeId;

    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;
}
