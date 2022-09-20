package com.todo.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

  public NotFoundException() {
    super("not.found");
  }

  public NotFoundException(String key) {
    super(key);
  }

  public NotFoundException(String key, String detailedMessage) {
    super(key == null ? "not.found" : key, detailedMessage);
    this.key = key;
    this.detailedMessage = detailedMessage;
  }

  public NotFoundException(String key, Throwable cause) {
    super(key == null ? "not.found" : key, cause);
  }
}