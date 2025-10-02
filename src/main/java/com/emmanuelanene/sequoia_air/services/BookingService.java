package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.dtos.BookingDTO;
import com.emmanuelanene.sequoia_air.dtos.CreateBookingRequest;
import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.enums.BookingStatus;

import java.util.List;

public interface BookingService {

    Response<?> createBooking(CreateBookingRequest createBookingRequest);
    Response<BookingDTO> getBookingById(Long id);
    Response<List<BookingDTO>> getAllBookings();
    Response<List<BookingDTO>> getMyBookings();
    Response<?> updateBookingStatus(Long id, BookingStatus status);
}
