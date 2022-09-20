package com.todo.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

  public BadRequestException() {
    super("bad.request");
  }

  public BadRequestException(String key) {
    super(key);
  }

  public BadRequestException(String key, String detailedMessage) {
    super(key, detailedMessage);
  }

  public BadRequestException(String key, Throwable cause) {
    super(key, cause);
  }
}
