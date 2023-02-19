package com.eldarbay.maindbworker.outbox.service;


import com.eldarbay.maindbworker.outbox.OutboxEvent;

public interface OutboxService {
  void save(OutboxEvent event);
}
