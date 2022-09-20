package com.todo.service;

import com.todo.entity.User;
import com.todo.entity.dto.UserPayload;
import com.todo.exception.base.dto.ValidationExceptionDTO;
import com.todo.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.todo.utils.LoggerUtils.loggerFor;

@Service
public class UserService extends BaseService<User> {

  private final Logger logger = loggerFor(this);
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    super(userRepository);
    this.userRepository = userRepository;
  }

  public ResponseEntity<?> list() {
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll().stream().map(User::toDTO));
  }

  @Transactional
  public ResponseEntity<Object> signup(UserPayload userPayload) {
    Optional<User> optionalUser = userRepository.findByEmail(userPayload.email());

    if (optionalUser.isPresent()) {
      var user = optionalUser.get();

      logger.error("User already exists. User: {}", user);

      return ResponseEntity
        .status(HttpStatus.EXPECTATION_FAILED)
        .body(new ValidationExceptionDTO("expectation.failed", "user.already.exists"));
    } else {
      logger.info("Saving User... User: {}", userPayload);

      User user = userRepository.save(userPayload.toEntity());

      return ResponseEntity.status(HttpStatus.OK).body(user.toDTO());
    }
  }
}