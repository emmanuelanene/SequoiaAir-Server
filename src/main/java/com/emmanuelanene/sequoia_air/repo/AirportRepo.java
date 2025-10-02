package com.emmanuelanene.sequoia_air.repo;

import com.emmanuelanene.sequoia_air.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepo extends JpaRepository<Airport, Long> {

    Optional<Airport> findByIataCode(String iataCode);
}
