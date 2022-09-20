package com.todo.exception.base;

public class ValidationException extends BaseException {

  public ValidationException() {
    super("validation.exception");
  }

  public ValidationException(String key) {
    super(key);
  }

  public ValidationException(String key, String detailedMessage) {
    super(key == null ? "validation.exception" : key, detailedMessage);
  }

  public ValidationException(String key, Throwable cause) {
    super(key, cause);
  }
}