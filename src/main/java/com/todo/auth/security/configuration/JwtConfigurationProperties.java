package com.todo.auth.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Instant;
import java.time.OffsetDateTime;

@ConfigurationProperties(prefix = "jwt.properties")
public class JwtConfigurationProperties {
  private String prefixAuthorizationHeader;
  private String authorizationType;
  private String signTokenPassword;
  private Long jwtExpirationTime;

  public String signTokenPassword() {
    return signTokenPassword;
  }

  public void setSignTokenPassword(String signTokenPassword) {
    this.signTokenPassword = signTokenPassword;
  }

  public Instant jwtExpirationTime() {
    return OffsetDateTime
      .now()
      .toInstant()
      .plusMillis(jwtExpirationTime);
  }

  public void setJwtExpirationTime(Long jwtExpirationTime) {
    this.jwtExpirationTime = jwtExpirationTime;
  }

  public String authorizationHeaderPrefix() {
    return prefixAuthorizationHeader;
  }

  public void setPrefixAuthorizationHeader(String prefixAuthorizationHeader) {
    this.prefixAuthorizationHeader = prefixAuthorizationHeader;
  }

  public String authorization() {
    return authorizationType;
  }

  public void setAuthorizationType(String authorizationType) {
    this.authorizationType = authorizationType;
  }
}