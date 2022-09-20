package com.todo.exception.base;

import lombok.Getter;
import org.springframework.lang.Nullable;

public abstract class BaseException extends RuntimeException {

  @Getter
  protected String key;
  @Getter
  protected String detailedMessage;

  public BaseException() {}

  public BaseException(String key) {
    super(key);
    this.key = key;
    this.detailedMessage = null;
  }

  public BaseException(String key, String detailedMessage) {
    super(key);
    this.key = key;
    this.detailedMessage = detailedMessage;
  }

  public BaseException(String key, Throwable cause) {
    super(key, cause);
    this.key = key;
    this.detailedMessage = null;
  }

  public BaseException(String key, String detailedMessage, @Nullable Throwable cause) {
    super(key, cause);
    this.key = key;
    this.detailedMessage = detailedMessage;
  }

  public BaseException(String key, @Nullable Throwable cause, Boolean enableSuppression, Boolean writableStackTrace) {
    super(key, cause, enableSuppression, writableStackTrace);
    this.key = key;
    this.detailedMessage = null;
  }
}