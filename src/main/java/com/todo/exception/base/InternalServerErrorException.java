package com.todo.exception.base;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends BaseException {

  public InternalServerErrorException() {
    super("bad.request");
  }

  public InternalServerErrorException(String key) {
    super(key);
  }

  public InternalServerErrorException(@Nullable String key, String detailedMessage) {
    super(key == null ? "internal.server.error" : key, detailedMessage);
  }

  public InternalServerErrorException(String key, Throwable cause) {
    super(key, cause);
  }
}