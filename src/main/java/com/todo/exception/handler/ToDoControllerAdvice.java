package com.todo.exception.handler;

import com.todo.exception.base.ApiErrorDTO;
import com.todo.exception.base.NotFoundException;
import com.todo.exception.base.ValidationException;
import com.todo.exception.base.dto.NotFoundExceptionDTO;
import com.todo.exception.base.dto.ValidationExceptionDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class ToDoControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotFoundException.class})
  public ResponseEntity<NotFoundExceptionDTO> handleException(NotFoundException exception) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(new NotFoundExceptionDTO(exception.getKey(), exception.getDetailedMessage()));
  }

  @ExceptionHandler(value = {ValidationException.class})
  public ResponseEntity<ValidationExceptionDTO> handleException(ValidationException exception) {
    return ResponseEntity
      .status(HttpStatus.EXPECTATION_FAILED)
      .body(new ValidationExceptionDTO(exception.getKey(), exception.getDetailedMessage()));
  }

  @Override
  protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
    @NotNull MethodArgumentNotValidException exception,
    @NotNull HttpHeaders headers,
    @NotNull HttpStatus status,
    @NotNull WebRequest request
  ) {
    Set<String> errors = new HashSet<>();
    BindingResult result = exception.getBindingResult();

    result.getFieldErrors().forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
    result.getGlobalErrors().forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

    ApiErrorDTO error = new ApiErrorDTO(HttpStatus.EXPECTATION_FAILED, errors);

    return handleExceptionInternal(exception, error, headers, error.getStatus(), request);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException exception) {
    Set<String> errors = new HashSet<>();

    exception.getConstraintViolations().forEach(violation -> {
      String name = violation.getRootBeanClass().getName();
      Path path = violation.getPropertyPath();
      String message = violation.getMessage();

      errors.add(name + " " + path + ": " + message);
    });

    ApiErrorDTO error = new ApiErrorDTO(HttpStatus.BAD_REQUEST, errors);

    return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
  }
}