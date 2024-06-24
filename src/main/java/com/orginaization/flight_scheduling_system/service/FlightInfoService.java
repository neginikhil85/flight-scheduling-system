package com.orginaization.flight_scheduling_system.service;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FlightInfoService {
    void create(FlightInfo flightInfo);
    List<FlightInfo> getAllFlights(int pageNo, int size);
    void postFlightInfoAsync(FlightInfo flightInfo);
}
