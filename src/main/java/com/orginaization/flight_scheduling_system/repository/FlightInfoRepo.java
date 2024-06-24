package com.orginaization.flight_scheduling_system.repository;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightInfoRepo extends JpaRepository<FlightInfo, Long> {

    Optional<FlightInfo> findByStartDateAndStartTerminal(LocalDateTime flightDate, String startTerminal);
}
