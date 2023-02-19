package com.eldarbay.maindbworker;

import com.eldarbay.maindbworker.domain.Person;
import com.eldarbay.maindbworker.dto.PersonDTO;
import com.eldarbay.maindbworker.outbox.domain.Outbox;
import com.eldarbay.maindbworker.outbox.repo.OutboxRepo;
import com.eldarbay.maindbworker.service.PersonService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class OutboxTests {

  @Autowired
  private PersonService personService;

  @Autowired
  private OutboxRepo repo;


  @Test
  void saveEvent() {
    PersonDTO personDTO = new PersonDTO(null, "Tamuna");
    PersonDTO savedPerson = personService.save(personDTO);
     List<Outbox> outbox = repo.findAllByAggregateId(savedPerson.getId().toString());
     Assertions.assertEquals(Person.class.getSimpleName(), outbox.get(0).getAggregateType());
  }

}
