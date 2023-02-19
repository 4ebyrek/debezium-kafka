package com.eldarbay.maindbworker.outbox.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import com.eldarbay.maindbworker.outbox.OutboxEvent;
import com.eldarbay.maindbworker.outbox.domain.Outbox;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Value;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public abstract class OutboxMapper {

  @Value("${outbox.publisher.name}")
  private String publisher;

  @Value("${spring.application.name}")
  private String appName;

  @Mapping(source = "aggregateType", target = "aggregateType")
  @Mapping(source = "aggregateId", target = "aggregateId")
  @Mapping(source = "eventType", target = "eventType")
  @Mapping(source = "eventGroup", target = "eventGroup")
  @Mapping(source = "payload", target = "payload")
  public abstract Outbox mapToEntity (OutboxEvent event);

  @AfterMapping
  protected void addPublisher(@MappingTarget Outbox outbox) {
    outbox.setPublisher(appName + "_" + publisher);
  }
}
