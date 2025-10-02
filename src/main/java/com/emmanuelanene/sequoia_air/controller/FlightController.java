package com.emmanuelanene.sequoia_air.controller;


import com.emmanuelanene.sequoia_air.dtos.*;
import com.emmanuelanene.sequoia_air.enums.City;
import com.emmanuelanene.sequoia_air.enums.Country;
import com.emmanuelanene.sequoia_air.enums.FlightStatus;
import com.emmanuelanene.sequoia_air.services.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")
    public ResponseEntity<Response<?>> createFlight(@Valid @RequestBody CreateFlightRequest createFlightRequest){
        return ResponseEntity.ok(flightService.createFlight(createFlightRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FlightDTO>> getFlightById(@PathVariable Long id){
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping
    public ResponseEntity<Response<List<FlightDTO>>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")
    public ResponseEntity<Response<?>> updateFlight(@RequestBody CreateFlightRequest flightRequest){
        return ResponseEntity.ok(flightService.updateFlight(flightRequest));
    }


    @GetMapping("/search")
    public ResponseEntity<Response<List<FlightDTO>>> searchFlight(
            @RequestParam(required = true) String departureIataCode,
            @RequestParam(required = true) String arrivalIataCode,
            @RequestParam(required = false, defaultValue = "SCHEDULED") FlightStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate
    ){
        return ResponseEntity.ok(flightService.searchFlight(departureIataCode, arrivalIataCode, status, departureDate));
    }

    @GetMapping("/cities")
    public ResponseEntity<Response<List<City>>> getAllCities() {
        return ResponseEntity.ok(flightService.getAllCities());
    }

    @GetMapping("/countries")
    public ResponseEntity<Response<List<Country>>> getAllCountries() {
        return ResponseEntity.ok(flightService.getAllCountries());
    }
}








