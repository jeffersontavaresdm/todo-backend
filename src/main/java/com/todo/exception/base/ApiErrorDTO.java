package com.todo.exception.base;

import com.todo.exception.base.dto.BaseExceptionDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Set;

public class ApiErrorDTO implements BaseExceptionDTO {

  @Getter
  private final HttpStatus status;
  @Getter
  private final Set<String> errors;

  public ApiErrorDTO(HttpStatus status, Set<String> errors) {
    this.status = status;
    this.errors = errors;
  }

  public ApiErrorDTO(HttpStatus status, String error) {
    this.status = status;
    errors = Set.of(error);
  }
}