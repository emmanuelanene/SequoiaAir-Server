package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.dtos.CreateFlightRequest;
import com.emmanuelanene.sequoia_air.dtos.FlightDTO;
import com.emmanuelanene.sequoia_air.dtos.Response;
import com.emmanuelanene.sequoia_air.enums.City;
import com.emmanuelanene.sequoia_air.enums.Country;
import com.emmanuelanene.sequoia_air.enums.FlightStatus;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    Response<?> createFlight(CreateFlightRequest createFlightRequest);
    Response<FlightDTO> getFlightById(Long id);
    Response<List<FlightDTO>> getAllFlights();
    Response<?> updateFlight(CreateFlightRequest createFlightRequest);
    Response<List<FlightDTO>> searchFlight(String departurePortIata, String arrivalPortIata, FlightStatus status, LocalDate departureDate);
    Response<List<City>> getAllCities();
    Response<List<Country>> getAllCountries();

}
