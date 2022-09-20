package com.todo.exception.base.dto;

import lombok.Getter;

public class NotFoundExceptionDTO implements BaseExceptionDTO {
  @Getter
  String key;

  @Getter
  String detailedError;

  public NotFoundExceptionDTO() {}

  public NotFoundExceptionDTO(String key) {
    this.key = key;
  }

  public NotFoundExceptionDTO(String key, String detailedMessage) {
    this.key = (key != null) ? key : "not.found";
    this.detailedError = (detailedMessage != null) ? detailedMessage : this.key;
  }
}