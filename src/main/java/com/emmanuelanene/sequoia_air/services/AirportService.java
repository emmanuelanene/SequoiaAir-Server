package com.emmanuelanene.sequoia_air.services;

import com.emmanuelanene.sequoia_air.dtos.AirportDTO;
import com.emmanuelanene.sequoia_air.dtos.Response;

import java.util.List;

public interface AirportService {

    Response<?> createAirport(AirportDTO airportDTO);

    Response<?> updateAirport(AirportDTO airportDTO);

    Response<List<AirportDTO>> getAllAirports();

    Response<AirportDTO> getAirportById(Long id);

}
