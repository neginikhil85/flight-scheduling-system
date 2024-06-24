package com.orginaization.flight_scheduling_system.rabbit.consumer.handler;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import com.orginaization.flight_scheduling_system.repository.FlightInfoRepo;
import com.orginaization.flight_scheduling_system.service.FlightInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlightInfoHandler {

    private final FlightInfoService flightInfoService;


    public void handle(Message<FlightInfo> flightInfoMessage) {
        if (Objects.nonNull(flightInfoMessage.getPayload())) {
            flightInfoService.create(flightInfoMessage.getPayload());
        } else {
            log.warn("Empty payload received");
        }
    }
}
