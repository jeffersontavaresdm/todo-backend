package com.todo.exception.not_found;

import com.todo.exception.base.NotFoundException;

public class ToDoNotFoundException extends NotFoundException {

  public ToDoNotFoundException() {
  }

  public ToDoNotFoundException(String key) {
    super(key);
  }

  public ToDoNotFoundException(String key, String detailedMessage) {
    super(key, detailedMessage);
    this.key = key;
    this.detailedMessage = (detailedMessage == null) ? "" : detailedMessage;
  }
}