package com.emmanuelanene.sequoia_air.dtos;

import com.emmanuelanene.sequoia_air.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;

    private String bookingReference;

    private UserDTO user;

    private FlightDTO flight;

    private LocalDateTime bookingDate;

    private BookingStatus status;

    private List<PassengerDTO> passengers;
}
