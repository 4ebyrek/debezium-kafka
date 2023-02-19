package com.eldarbay.maindbworker.outbox;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class OutboxEvent implements Serializable {
  @Serial
  private static final long serialVersionUID = 4588320535877794806L;

  private UUID correlationId;

  private String aggregateType;

  private String aggregateId;

  private String eventType;

  private String eventGroup;

  private JsonNode payload;

  public OutboxEvent(
      UUID correlationId,
      String aggregateType,
      String aggregateId,
      String eventType,
      String eventGroup,
      JsonNode payload
  ) {
    this.correlationId = correlationId == null ? UUID.randomUUID() : correlationId;
    this.aggregateType = aggregateType;
    this.aggregateId = aggregateId;
    this.eventType = eventType;
    this.eventGroup = eventGroup;
    this.payload = payload;
  }
}
