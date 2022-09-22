package com.todo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.entity.TODO;
import com.todo.entity.User;
import com.todo.entity.dto.TODODTO;
import com.todo.entity.dto.TODOPayload;
import com.todo.exception.base.InternalServerErrorException;
import com.todo.exception.base.ValidationException;
import com.todo.exception.base.dto.NotFoundExceptionDTO;
import com.todo.exception.base.dto.NothingFoundResponse;
import com.todo.exception.not_found.ToDoNotFoundException;
import com.todo.repository.TODORepository;
import com.todo.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.todo.utils.LoggerUtils.loggerFor;

@Service
public class TODOService extends BaseService<TODO> {

  private final TODORepository todoRepository;
  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;

  public TODOService(
    TODORepository todoRepository,
    UserRepository userRepository,
    @Qualifier(value = "defaultObjectMapper") ObjectMapper objectMapper
  ) {
    super(todoRepository);
    this.todoRepository = todoRepository;
    this.userRepository = userRepository;
    this.objectMapper = objectMapper;
  }

  private final Logger logger = loggerFor(this);

  @Transactional
  public ResponseEntity<?> create(@NotNull TODOPayload toDoPayload, String principal) {
    User data;

    try {
      data = objectMapper.readValue(principal, User.class);
    } catch (IOException exception) {
      logger.error("Error when trying to deserialize the user. User: {}", principal);

      throw new InternalServerErrorException(null, exception.getMessage());
    }

    var user = userRepository.findByKeyAndEmail(
      data.getKey(),
      data.getEmail()
    );

    boolean todoAlreadyExists = (todoRepository.getByName(toDoPayload.getName()).orElse(null) != null);

    if (!todoAlreadyExists) {
      logger.info("Creating a new todo. username: {}", toDoPayload.getName());

      if (user.isPresent()) {
        TODO todo = repository.save(toDoPayload.toEntity(user.get()));

        return ResponseEntity.status(HttpStatus.CREATED).body(todo.toDTO());
      } else {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
      }
    } else {
      logger.error("this TODO already exists");

      throw new ValidationException(null, "This todo alread exists.");
    }
  }

  public ResponseEntity<?> findAll() {
    List<TODODTO> todoDTOsOrEmptyList = repository
      .findAll()
      .stream()
      .map(TODO::toDTO)
      .toList();

    return (!todoDTOsOrEmptyList.isEmpty())
      ? ResponseEntity.ok().body(todoDTOsOrEmptyList)
      : ResponseEntity.status(HttpStatus.OK).body(new NothingFoundResponse(null, "todo list is empty"));
  }

  public ResponseEntity<?> getById(Long id) {
    TODO todo = findByIdOrNull(id);

    return (todo != null)
      ? ResponseEntity.status(HttpStatus.OK).body(todo.toDTO())
      : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundExceptionDTO("not.found", "id not found"));
  }

  public ResponseEntity<?> getByName(String name) {
    Optional<TODO> todo = todoRepository.getByName(name);

    if (todo.isPresent()) {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(todo.get().toDTO());
    } else {
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new NotFoundExceptionDTO("not.found", "username not found"));
    }
  }

  @Transactional
  public ResponseEntity<?> update(TODOPayload todoPayload, Long todoId) {
    TODO toDo = findByIdOrNull(todoId);

    if (toDo != null) {
      TODO savedTODO = repository.save(
        toDo.copy(
          todoPayload.getName(),
          todoPayload.getStartDate(),
          todoPayload.getEndDate()
        )
      );

      logger.info("To Do was updated successfully. ToDo: {}", savedTODO);

      return ResponseEntity.ok().build();
    } else {
      logger.error("To Do with id '{}' not found.", todoId);

      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new NotFoundExceptionDTO("not.found", "id not found"));
    }
  }

  @Transactional
  public ResponseEntity<?> delete(Long id) {
    try {
      logger.info("Deleting TODO with id '{}'", id);

      repository.deleteById(id);
    } catch (RuntimeException exception) {
      logger.error("CAUSE: {}", exception.getMessage());

      throw new ToDoNotFoundException("todo.not.found", "id '" + id + "' not found");
    }

    return ResponseEntity
      .status(HttpStatus.NO_CONTENT)
      .build();
  }
}