package com.emmanuelanene.sequoia_air.repo;

import com.emmanuelanene.sequoia_air.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByUserIdOrderByIdDesc(Long userId);
}
