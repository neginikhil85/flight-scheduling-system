package com.orginaization.flight_scheduling_system.service.impl;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import com.orginaization.flight_scheduling_system.rabbit.producer.event.FlightProducer;
import com.orginaization.flight_scheduling_system.repository.FlightInfoRepo;
import com.orginaization.flight_scheduling_system.service.FlightInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightInfoServiceImpl implements FlightInfoService {

    private final FlightInfoRepo flightInfoRepo;
    private final FlightProducer flightProducer;

    @Override
    public void create(FlightInfo flightInfo) {
        if (Objects.isNull(flightInfo.getStartDate()) && Objects.isNull(flightInfo.getEndDate())
                && !StringUtils.hasText(flightInfo.getStartTerminal())
                && !StringUtils.hasText(flightInfo.getEndTerminal())) {
            throw new IllegalArgumentException("flight details are missing");
        }

        Optional<FlightInfo> flightInfoOptional = flightInfoRepo.findByStartDateAndStartTerminal(
                flightInfo.getStartDate(), flightInfo.getStartTerminal());

        if (flightInfoOptional.isEmpty()) {
            flightInfoRepo.save(flightInfo);
        } else {
            log.warn("[FlightInfoServiceImpl::create] flight details already exists");
        }
    }

    @Override
    public List<FlightInfo> getAllFlights(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return flightInfoRepo.findAll(pageable).getContent();
    }

    @Override
    public void postFlightInfoAsync(FlightInfo flightInfo) {
        flightProducer.sendFlightDetails(flightInfo);
    }
}
