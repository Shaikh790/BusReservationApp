package com.reservationapp.controller;


import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.payload.BusDto;

import com.reservationapp.payload.SearchlistOfBusesDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
 @Autowired
 private RouteRepository routeRepository;

 @Autowired
 private SubRouteRepository subRouteRepository;

@Autowired
private BusRepository busRepository;
    @Autowired
    private BusService busService;
//http://localhost:8881/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<Bus> addBus(@RequestBody BusDto busDto) throws ParseException {
// Parse date strings using SimpleDateFormat

       Bus bus = busService.addBus(busDto);
        return new ResponseEntity<>(bus,HttpStatus.CREATED);
    }
    //http://localhost:8881/api/v1/bus?fromLocation=&toLocation=&fromdate=
    @GetMapping
    public  List<SearchlistOfBusesDto> getAllBuses(@RequestParam String fromLocation,
                                                   @RequestParam String toLocation,
                                                   @RequestParam  String fromDate){

        List<Route> routes=routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,toLocation,fromDate);
        List<SubRoute> subRoutes=subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation,toLocation,fromDate);
//        System.out.println(routes);
//        return busRepository.findAll();
        List<SearchlistOfBusesDto> buses= new ArrayList<>();
        if (routes!=null) {

            for (Route route : routes) {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchlistOfBusesDto searchlistOfBusesDto = mapToSearchListOfBusesDto(bus, route);
                buses.add(searchlistOfBusesDto);
            }
            return buses;
        }
        if (subRoutes!=null) {

            for (SubRoute route : subRoutes) {
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchlistOfBusesDto searchlistOfBusesDto = mapToSearchListOfBusesDto(bus,route);
                buses.add(searchlistOfBusesDto);
            }
            return buses;
        }
        return null;

    }
   SearchlistOfBusesDto mapToSearchListOfBusesDto(Bus bus,Route route){
        SearchlistOfBusesDto searchlistOfBusesDto = new SearchlistOfBusesDto();
        searchlistOfBusesDto.setBusId(bus.getId());
        searchlistOfBusesDto.setBusNumber(bus.getBusNumber());
        searchlistOfBusesDto.setPrice(bus.getPrice());
        searchlistOfBusesDto.setBusType(bus.getBusType());
        searchlistOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchlistOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchlistOfBusesDto.setFromLocation(route.getFromLocation());
        searchlistOfBusesDto.setToLocation(route.getToLocation());
        searchlistOfBusesDto.setFromDate(route.getFromDate());
        searchlistOfBusesDto.setToDate(route.getToDate());
        searchlistOfBusesDto.setFromTime(route.getFromTime());
        searchlistOfBusesDto.setToTime(route.getToTime());
        searchlistOfBusesDto.setRouteId(route.getId());
        searchlistOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchlistOfBusesDto.setFromTime(route.getFromTime());
        searchlistOfBusesDto.setToTime(route.getToTime());
        return  searchlistOfBusesDto;
   }
    SearchlistOfBusesDto mapToSearchListOfBusesDto(Bus bus,SubRoute route){
        SearchlistOfBusesDto searchlistOfBusesDto = new SearchlistOfBusesDto();
        searchlistOfBusesDto.setBusId(bus.getId());
        searchlistOfBusesDto.setBusNumber(bus.getBusNumber());
        searchlistOfBusesDto.setPrice(bus.getPrice());
        searchlistOfBusesDto.setBusType(bus.getBusType());
        searchlistOfBusesDto.setAvailableSeats(bus.getAvailableSeats());
        searchlistOfBusesDto.setTotalSeats(bus.getTotalSeats());
        searchlistOfBusesDto.setFromLocation(route.getFromLocation());
        searchlistOfBusesDto.setToLocation(route.getToLocation());
        searchlistOfBusesDto.setFromDate(route.getFromDate());
        searchlistOfBusesDto.setToDate(route.getToDate());
        searchlistOfBusesDto.setFromTime(route.getFromTime());
        searchlistOfBusesDto.setToTime(route.getToTime());
        searchlistOfBusesDto.setRouteId(route.getId());
        searchlistOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchlistOfBusesDto.setFromTime(route.getFromTime());
        searchlistOfBusesDto.setToTime(route.getToTime());
        return  searchlistOfBusesDto;
    }

}
