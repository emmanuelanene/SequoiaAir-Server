package com.emmanuelanene.sequoia_air.repo;

import com.emmanuelanene.sequoia_air.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {
}
