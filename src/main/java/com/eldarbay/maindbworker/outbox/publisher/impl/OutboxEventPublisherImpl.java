package com.eldarbay.maindbworker.outbox.publisher.impl;

import com.eldarbay.maindbworker.outbox.OutboxEvent;
import com.eldarbay.maindbworker.outbox.publisher.OutboxEventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class OutboxEventPublisherImpl implements OutboxEventPublisher {

  private final ApplicationEventPublisher publisher;

  @Override
  public void publish(OutboxEvent event) {
    publisher.publishEvent(event);
    log.info("Published Event with id = {}, with payload = {}",
        event.getCorrelationId(),
        event
    );
  }
}
