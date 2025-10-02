package com.emmanuelanene.sequoia_air.repo;

import com.emmanuelanene.sequoia_air.entities.Flight;
import com.emmanuelanene.sequoia_air.enums.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepo extends JpaRepository<Flight, Long> {

    boolean existsByFlightNumber(String flightNumber);

    List<Flight> findByDepartureAirportIataCodeAndArrivalAirportIataCodeAndStatusAndDepartureTimeBetween(
            String departureIataCode,
            String arrivalIataCode,
            FlightStatus status,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );


}
