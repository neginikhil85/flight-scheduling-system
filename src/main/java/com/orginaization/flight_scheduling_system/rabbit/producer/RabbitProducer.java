package com.orginaization.flight_scheduling_system.rabbit.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitProducer {

    private final StreamBridge streamBridge;

    public void send(String bindingName, Object payload) {
        if (ObjectUtils.isEmpty(bindingName) || Objects.isNull(payload)) {
            log.error("[RabbitProducer] bindingName: {}, Payload: {} can't be NULL/EMPTY", bindingName, payload);
            return;
        }
        try {
            streamBridge.send(bindingName, payload);
        } catch (Exception e) {
            log.error("[RabbitProducer] Error occurred while sending the payload to rabbit mq");
        }
    }
}
