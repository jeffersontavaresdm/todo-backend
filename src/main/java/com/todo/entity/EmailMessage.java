package com.todo.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Getter
public class EmailMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;
  private OffsetDateTime sentAt;

  public EmailMessage() {}

  public EmailMessage(String message) {
    this.message = message;
    this.sentAt = OffsetDateTime.now();
  }
}