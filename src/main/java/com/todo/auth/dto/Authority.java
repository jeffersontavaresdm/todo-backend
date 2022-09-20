package com.todo.auth.dto;

import com.todo.auth.UserDetailsData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class Authority implements GrantedAuthority {
  private UserDetailsData user;
  private String role;

  @Override
  public String getAuthority() {
    return this.getRole();
  }
}