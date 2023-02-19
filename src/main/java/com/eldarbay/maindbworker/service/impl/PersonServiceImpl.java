package com.eldarbay.maindbworker.service.impl;

import com.eldarbay.maindbworker.domain.Person;
import com.eldarbay.maindbworker.dto.PersonDTO;
import com.eldarbay.maindbworker.outbox.OutboxEvent;
import com.eldarbay.maindbworker.outbox.publisher.OutboxEventPublisher;
import com.eldarbay.maindbworker.repo.PersonRepo;
import com.eldarbay.maindbworker.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepo personRepo;

  @Autowired
  private OutboxEventPublisher eventPublisher;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @Transactional
  public PersonDTO save(PersonDTO personDTO) {
    Person person = new Person(personDTO.getName());
    person = personRepo.save(person);
    eventPublisher.publish(createEvent(person));
    PersonDTO result = new PersonDTO(person.getId(), person.getName());
    return result;
  }

  private OutboxEvent createEvent(Person person) {
    return new OutboxEvent(
        null,
        person.getClass().getSimpleName(),
        person.getId().toString(),
        "PERSON_CREATED",
        "person_event",
        objectMapper.valueToTree(person)
    );
  }
}
