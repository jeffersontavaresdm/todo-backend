package com.todo.auth.service;

import com.todo.auth.UserDetailsData;
import com.todo.entity.User;
import com.todo.repository.UserRepository;
import com.todo.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe possui um metodo responsável por verificar se os dados do usuário está no banco de dados e retornar os detalhes dele.
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {

  private final Logger logger = LoggerUtils.loggerFor(this);

  private final UserRepository userRepository;

  public UserDetailsServiceImp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(email);

    if (user.isPresent()) {
      logger.info("Getting User details. User: {}", email);

      return new UserDetailsData(user.get());
    } else {
      logger.info("User with email '{}' not found!", email);

      throw new UsernameNotFoundException("user.not.found");
    }
  }
}