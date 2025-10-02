package com.emmanuelanene.sequoia_air.controller;


import com.emmanuelanene.sequoia_air.dtos.AirportDTO;
import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.services.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<?>> createAirport(@Valid @RequestBody AirportDTO airportDTO){
        return ResponseEntity.ok(airportService.createAirport(airportDTO));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response<?>> updateAirport(@RequestBody AirportDTO airportDTO){
        return ResponseEntity.ok(airportService.updateAirport(airportDTO));
    }

    @GetMapping
    public ResponseEntity<Response<List<AirportDTO>>> getAllAirports(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response<AirportDTO>> getAirportById(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getAirportById(id));
    }



}
