package com.todo.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class ApplicationUserDetails extends User implements Serializable {
  private final String username;
  private final String password;
  private final String accessToken;

  private UserDetailProperties properties;

  private Collection<Authority> authorities;

  public ApplicationUserDetails(String username, String password, Collection<Authority> authorities) {
    super(username, password, authorities);
    this.username = username;
    this.password = password;
    this.accessToken = username;
    this.authorities = authorities;
  }

  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }
}