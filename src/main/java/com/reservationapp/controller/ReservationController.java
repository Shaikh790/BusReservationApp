package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Passenger;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.payload.ReservationDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class ReservationController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private BusRepository busRepository;

    public ReservationDto bookTicket(
            @RequestParam long busId,
            @RequestParam long routeId,
            @RequestParam String SeatNumber
    ){
//       Bus bus = busRepository.findById(busId).orElseThrow(
//               ()-> new ResourceNotFound("bus not found with id")
//        );
//        Route route = routeRepository.findById(routeId).orElseThrow(
//                ()-> new ResourceNotFound("route  not found with id")
//        );
         boolean busIsPresent=false;
         boolean routeIsPresent=false;
        boolean subrouteIsPresent=false;
        Optional<Bus> byId =busRepository.findById(busId);
        if (byId.isPresent()){
            busIsPresent=true;
            Bus bus = byId.get();
        }
        Optional<Route> byRouteId = routeRepository.findById(routeId);
        if (byRouteId.isPresent()){
            routeIsPresent=true;
            Bus bus= byId.get();
        }
        Optional<SubRoute> bySubRouteId = subRouteRepository.findById(routeId);
        if (byRouteId.isPresent()){
            subrouteIsPresent=true;
          Bus bus= byId.get();
        }
        if (busIsPresent&&routeIsPresent || busIsPresent&&subrouteIsPresent){
            //add passenger

        }
        return  null ;
    }
}
