package com.orginaization.flight_scheduling_system.rabbit.producer.event;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import com.orginaization.flight_scheduling_system.rabbit.producer.RabbitProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightProducer {

    private static final String BINDING_NAME = "flightProducer-out-0";

    private final RabbitProducer rabbitProducer;

    public void sendFlightDetails(FlightInfo flightInfo) {
        log.info("[FlightProducer] flight details : {}", flightInfo);
        rabbitProducer.send(BINDING_NAME, flightInfo);
    }
}
