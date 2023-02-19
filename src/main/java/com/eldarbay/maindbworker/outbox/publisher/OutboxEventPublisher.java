package com.eldarbay.maindbworker.outbox.publisher;

import com.eldarbay.maindbworker.outbox.OutboxEvent;
import jakarta.validation.Valid;

public interface OutboxEventPublisher {
  void publish(@Valid OutboxEvent event);
}
