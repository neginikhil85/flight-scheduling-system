package com.orginaization.flight_scheduling_system.controller;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import com.orginaization.flight_scheduling_system.service.FlightInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightInfoController {

    private final FlightInfoService flightInfoService;

    @PostMapping
    public void postFlightInfo(@RequestBody FlightInfo flightInfo) {
        flightInfoService.create(flightInfo);
    }

    @GetMapping
    public ResponseEntity<List<FlightInfo>> getAllFlightInfo(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(flightInfoService.getAllFlights(pageNo, size));
    }

    @PostMapping("/async")
    public void postFlightInfoAsync(@RequestBody FlightInfo flightInfo) {
        flightInfoService.postFlightInfoAsync(flightInfo);
    }
}
