package com.eldarbay.maindbworker.outbox.domain;

import com.eldarbay.maindbworker.outbox.domain.converter.JsonNodeConverter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "outbox")
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Outbox {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_seq")
  @SequenceGenerator(name = "outbox_seq", sequenceName = "outbox_event_sequence")
  private Long id;

  @Column(name = "publisher", nullable = false)
  private String publisher;

  @Column(name = "aggregate_type", nullable = false)
  private String aggregateType;

  @Column(name = "aggregate_id", nullable = false)
  private String aggregateId;

  @Column(name = "event_type", nullable = false)
  private String eventType;

  @Column(name = "event_group", nullable = false)
  private String eventGroup;

  @Convert(converter = JsonNodeConverter.class)
  @Column(name = "payload", columnDefinition = "json", nullable = false, updatable = false)
  private JsonNode payload;

  @CreatedDate
  private LocalDateTime createdDate;
}
