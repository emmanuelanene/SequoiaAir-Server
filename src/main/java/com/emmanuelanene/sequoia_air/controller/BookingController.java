package com.emmanuelanene.sequoia_air.controller;


import com.emmanuelanene.sequoia_air.dtos.BookingDTO;
import com.emmanuelanene.sequoia_air.dtos.CreateBookingRequest;
import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.enums.BookingStatus;
import com.emmanuelanene.sequoia_air.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    public ResponseEntity<Response<?>> createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest){
        return ResponseEntity.ok(bookingService.createBooking(createBookingRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<BookingDTO>> getBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")
    public ResponseEntity<Response<List<BookingDTO>>> getAllBookings(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/me")
    public ResponseEntity<Response<List<BookingDTO>>> getMyBookings(){
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PILOT')")
    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateBookingStatus(@PathVariable Long id, @RequestBody BookingStatus status){
        return ResponseEntity.ok(bookingService.updateBookingStatus(id, status));
    }

}
