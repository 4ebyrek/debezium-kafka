package com.eldarbay.maindbworker.outbox.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(JsonNode payload) {
    String payloadJson = null;
    try {
      payloadJson = objectMapper.writeValueAsString(payload);
    } catch (final JsonProcessingException e) {
      log.error("JSON writing error", e);
    }

    return payloadJson;
  }

  @Override
  public JsonNode convertToEntityAttribute(String payloadJson) {
    JsonNode payload = null;
    try {
      payload = objectMapper.readTree(payloadJson);
    } catch (final IOException e) {
      log.error("JSON reading error", e);
    }

    return payload;
  }
}
