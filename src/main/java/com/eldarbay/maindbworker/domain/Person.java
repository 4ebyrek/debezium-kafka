package com.eldarbay.maindbworker.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Person implements Serializable {

  @Serial
  private static final long serialVersionUID = -748107391423376359L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
  @SequenceGenerator(name = "person_seq", sequenceName = "person_sequence")
  private Long id;
  private String name;

  public Person(String name) {
    this.name = name;
  }
}
