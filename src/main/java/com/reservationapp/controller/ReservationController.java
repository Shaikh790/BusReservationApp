package com.reservationapp.controller;

import com.reservationapp.entity.Bus;
import com.reservationapp.entity.Passenger;
import com.reservationapp.entity.Route;
import com.reservationapp.entity.SubRoute;
import com.reservationapp.exception.ResourceNotFound;
import com.reservationapp.payload.ReservationDto;
import com.reservationapp.repository.BusRepository;
import com.reservationapp.repository.PassengerRepository;
import com.reservationapp.repository.RouteRepository;
import com.reservationapp.repository.SubRouteRepository;
import com.reservationapp.util.EmailService;
import com.reservationapp.util.ExcelGeneratorService;
import com.reservationapp.util.PdfTicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private  PassengerRepository passengerRepository;
     @Autowired
     private PdfTicketGeneratorService pdfTicketGeneratorService;
     @Autowired
     private EmailService emailService;
     @Autowired
     private ExcelGeneratorService excelGeneratorService;

     //http://localhost:8881/api/reservation?busId=1&routeId=2
     @PostMapping
    public ResponseEntity<String> bookTicket(
            @RequestParam long busId,
            @RequestParam long routeId,
            @RequestBody Passenger passenger
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
        Passenger p = new Passenger();
        p.setFirstName(passenger.getFirstName());
        p.setLastName(passenger.getLastName());
        p.setEmail(passenger.getEmail());
        p.setMobile(passenger.getMobile());
        p.setRouteId(routeId);
        p.setBusId(busId);
            Passenger savedPassenger = passengerRepository.save(p);
            byte[] pdfBytes = pdfTicketGeneratorService.generateTicket(savedPassenger, byRouteId.get().getFromLocation(),
                    byRouteId.get().getToLocation(), byRouteId.get().getFromDate());
            emailService.sendEmailWithAttachment(passenger.getEmail(),
                    "booking Conform..","your reservation id"+savedPassenger.getId(),pdfBytes,"tickets");
        }
        return new ResponseEntity<>("Done..", HttpStatus.CREATED) ;
    }
    @GetMapping("/passengers/excel")
    public ResponseEntity<byte[]> generateExcel() {
        try {
            // Retrieve passenger data from database or any other source
            List<Passenger> passengers = fetchPassengersDataBase();

            // Generate Excel file using the service
            byte[] excelBytes = excelGeneratorService.generateExcel(passengers);

            // Prepare HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "passenger_data.xlsx");

            return new ResponseEntity<>(excelBytes,headers,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Example method to retrieve passenger data (replace this with actual data retrieval logic)
    private List<Passenger> fetchPassengersDataBase() {

        return passengerRepository.findAll();
    }
}

