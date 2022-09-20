package com.todo.auth.security.configuration;

import com.todo.auth.security.filter.JwtAuthenticateFilter;
import com.todo.auth.security.filter.JwtValidationFilter;
import com.todo.utils.EncryptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfiguration {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final JwtConfigurationProperties properties;

  public JwtSecurityConfiguration(
    AuthenticationConfiguration authenticationConfiguration,
    JwtConfigurationProperties properties
  ) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.properties = properties;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return EncryptionUtils.encoder();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http
      .csrf().disable()
      .cors()
      .and()
      .authorizeRequests()
      .antMatchers(HttpMethod.POST, "/api/user/signup").permitAll()
      .antMatchers(HttpMethod.POST, "/login").permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager(), properties))
      .addFilter(new JwtAuthenticateFilter(authenticationConfiguration.getAuthenticationManager(), properties))
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .build();
  }
}