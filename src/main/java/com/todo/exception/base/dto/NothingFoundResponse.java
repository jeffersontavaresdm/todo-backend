package com.todo.exception.base.dto;

import lombok.Getter;

public class NothingFoundResponse implements BaseExceptionDTO {
  @Getter
  private final String key;

  @Getter
  private final String message;

  public NothingFoundResponse() {
    this.key = "nothing.found";
    this.message = "";
  }

  public NothingFoundResponse(String key) {
    this.key = (key == null) ? "nothing.found" : key;
    this.message = "";
  }

  public NothingFoundResponse(String key, String message) {
    this.key = (key == null) ? "nothing.found" : key;
    this.message = (message == null) ? this.key : message;
  }
}