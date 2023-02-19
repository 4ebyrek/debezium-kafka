package com.eldarbay.maindbworker.outbox.listener;

import com.eldarbay.maindbworker.outbox.OutboxEvent;
import com.eldarbay.maindbworker.outbox.service.OutboxService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@AllArgsConstructor
@Slf4j
@Component
public class OutboxEventListener {

  private final OutboxService service;

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleOutBoxEvent(OutboxEvent event) {
    log.info("Received Event with id = {}, with payload = {}",
        event.getCorrelationId(),
        event
    );

    service.save(event);

    log.info("Saved Event with id = {}, with payload = {}",
        event.getCorrelationId(),
        event
    );
  }

}
