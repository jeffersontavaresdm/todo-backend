package com.todo.auth.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.todo.auth.security.configuration.JwtConfigurationProperties;
import com.todo.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Classe responsável por validar o token.
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {
  private final JwtConfigurationProperties properties;
  private final Logger logger = LoggerUtils.loggerFor(this);

  public JwtValidationFilter(
    AuthenticationManager authenticationManager,
    JwtConfigurationProperties properties
  ) {
    super(authenticationManager);
    this.properties = properties;
  }

  /**
   * Metodo para interceptar o cabeçalho da requisição.
   */
  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain
  ) throws ServletException, IOException {
    String authorization = request.getHeader(properties.authorization());

    if (authorization == null || !authorization.startsWith(properties.authorizationHeaderPrefix())) {
      chain.doFilter(request, response);
      return;
    }

    String token = authorization.replace(properties.authorizationHeaderPrefix(), "").trim();

    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    chain.doFilter(request, response);
  }

  /**
   * Método para fazer a leitura do token e retornar os dados do usuário para garantir que ele é um usuário válido.
   */
  private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
    String user = JWT
      .require(Algorithm.HMAC512(properties.signTokenPassword()))
      .build()
      .verify(token)
      .getSubject();

    if (user == null) return null;

    UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

    logger.info("User authenticated! User: {}", authenticatedUser);

    return authenticatedUser;
  }
}