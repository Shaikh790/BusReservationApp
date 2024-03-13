package com.reservationapp.service;

import com.reservationapp.entity.Bus;

import com.reservationapp.payload.BusDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository; // Assuming you have a repository for managing Bus entities
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

    @Transactional
    public Bus addBus(BusDto busDto) {
        // Create Bus Entity
        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAvailableSeats(busDto.getAvailableSeats());


        // Save Bus entity
        Bus saveBus = busRepository.save(bus);

        return saveBus;
    }
}
