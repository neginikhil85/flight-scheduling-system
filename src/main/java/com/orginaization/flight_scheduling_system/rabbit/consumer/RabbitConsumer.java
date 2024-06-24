package com.orginaization.flight_scheduling_system.rabbit.consumer;

import com.orginaization.flight_scheduling_system.entity.FlightInfo;
import com.orginaization.flight_scheduling_system.rabbit.consumer.handler.FlightInfoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class RabbitConsumer {

    @Bean
    public Consumer<Message<FlightInfo>> flightConsumer(FlightInfoHandler handler) {
        return handler::handle;
    }
}
