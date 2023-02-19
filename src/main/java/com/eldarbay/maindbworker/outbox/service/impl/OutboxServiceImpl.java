package com.eldarbay.maindbworker.outbox.service.impl;

import com.eldarbay.maindbworker.outbox.OutboxEvent;
import com.eldarbay.maindbworker.outbox.domain.Outbox;
import com.eldarbay.maindbworker.outbox.mapper.OutboxMapper;
import com.eldarbay.maindbworker.outbox.repo.OutboxRepo;
import com.eldarbay.maindbworker.outbox.service.OutboxService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Service
public class OutboxServiceImpl implements OutboxService {

  private final OutboxMapper mapper;
  private final OutboxRepo repo;


  @Transactional
  @Override
  public void save(OutboxEvent event) {
    Outbox outbox = mapper.mapToEntity(event);
    repo.save(outbox);
  }
}
