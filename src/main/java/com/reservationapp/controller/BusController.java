package com.reservationapp.controller;


import com.reservationapp.payload.BusDto;

import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {

    @Autowired
    private BusService busService;
//http://localhost:8881/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<BusDto> addBus(@RequestBody BusDto busDto) throws ParseException {
// Parse date strings using SimpleDateFormat

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date fromdate = dateFormat.parse(busDto.getFromDate());
        Date todate= dateFormat.parse(busDto.getToDate());
        BusDto dto = busService.addBus(busDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
}
