package com.todo.auth.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.auth.UserDetailsData;
import com.todo.auth.security.configuration.JwtConfigurationProperties;
import com.todo.auth.security.filter.dto.AuthenticationProperties;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.todo.utils.LoggerUtils.loggerFor;

/**
 * Classe responsável por autenticar e gerar o token jwt.
 */
public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtConfigurationProperties properties;
  private final Logger logger = loggerFor(this);

  public JwtAuthenticateFilter(
    AuthenticationManager authenticationManager,
    JwtConfigurationProperties properties
  ) {
    this.authenticationManager = authenticationManager;
    this.properties = properties;
  }

  /**
   * Método responsável por executar a autenticação.
   */
  @Override
  public Authentication attemptAuthentication(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws AuthenticationException {
    var objectMapper = new ObjectMapper().findAndRegisterModules();

    try {
      AuthenticationProperties user = objectMapper.readValue(request.getInputStream(), AuthenticationProperties.class);

      logger.info("Trying authenticate user. User: {}", user.email());

      var userAuthenticationToken = new UsernamePasswordAuthenticationToken(
        user.email(),
        user.password(),
        Collections.emptyList()
      );

      return authenticationManager.authenticate(userAuthenticationToken);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  protected void successfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain,
    Authentication authResult
  ) throws IOException {
    logger.info("creating a JWT token...");

    UserDetailsData user = (UserDetailsData) authResult.getPrincipal();

    String token = JWT
      .create()
      .withExpiresAt(properties.jwtExpirationTime())
      .withSubject(user.toString())
      .sign(Algorithm.HMAC512(properties.signTokenPassword()));

    logger.info("Token created successfully! Token: {}", token.split("\\.")[1]);

    response.getWriter().write(token);
    response.getWriter().flush();
  }
}