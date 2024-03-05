package com.reservationapp.service;

import com.reservationapp.entity.Bus;
import com.reservationapp.payload.BusDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private DriverRepository driverRepository;
    public  BusDto addBus(BusDto busDto){
        Bus bus = mapToEntity(busDto);
        driverRepository.save(busDto.getDriver());
        Bus saveBus = busRepository.save(bus);
       return  mapToDto(saveBus);
    }
   Bus mapToEntity(BusDto dto){
        Bus bus =new Bus();
       bus.setBusNumber(dto.getBusNumber());
       bus.setBusType(dto.getBusType());
       bus.setFrom(dto.getFrom());
       bus.setTo(dto.getTo());
       bus.setFromDate(dto.getFromDate());
       bus.setToDate(dto.getToDate());
       bus.setTotalDuration(dto.getTotalDuration());
       bus.setFromTime(dto.getFromTime());
       bus.setToTime(dto.getToTime());
       bus.setPrice(dto.getPrice());
       bus.setTotalSeats(dto.getTotalSeats());
       bus.setAvailableSeats(dto.getAvailableSeats());
       bus.setDriver(dto.getDriver());
       return bus;
    }
    BusDto mapToDto(Bus bus){
        BusDto dto =new BusDto();
        dto.setBusId(bus.getBusId());
        dto.setPrice(bus.getPrice());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setFrom(bus.getFrom());
        dto.setTo(bus.getTo());
        dto.setFromDate(bus.getFromDate());
        dto.setToDate(bus.getToDate());
        dto.setTotalDuration(bus.getTotalDuration());
        dto.setFromTime(bus.getFromTime());
        dto.setToTime(bus.getToTime());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());
        dto.setDriver(bus.getDriver());
        return  dto;
    }
}
