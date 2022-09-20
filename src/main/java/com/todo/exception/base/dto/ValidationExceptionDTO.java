package com.todo.exception.base.dto;

import org.springframework.lang.Nullable;

import java.util.Objects;

public class ValidationExceptionDTO implements BaseExceptionDTO {

  String key;

  String message;

  public ValidationExceptionDTO(String key, @Nullable String detailedMessage) {
    this.key = key.isBlank() ? "validation.exception" : key;
    this.message = Objects.requireNonNull(detailedMessage).isBlank() ? key : detailedMessage;
  }

  public String getKey() {
    return key;
  }

  public String getMessage() {
    return message;
  }
}