package com.eldarbay.maindbworker.outbox.repo;

import com.eldarbay.maindbworker.outbox.domain.Outbox;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepo extends JpaRepository<Outbox, Long> {
  List<Outbox> findAllByAggregateId(String aggregateId);
}
