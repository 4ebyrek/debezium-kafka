package com.eldarbay.maindbworker.api;

import com.eldarbay.maindbworker.dto.PersonDTO;
import com.eldarbay.maindbworker.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PersonController {

  @Autowired
  private PersonService personService;

  @PostMapping(value = "/person/create")
  public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
    PersonDTO savedPerson = personService.save(personDTO);
    log.info("Person saved = {}", savedPerson);
    return ResponseEntity.ok(savedPerson);
  }
  
}
