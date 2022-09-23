package com.todo.auth;

import com.todo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;

/**
 * Classe responsável por armazenar os detalhes dos dados do usuário.
 */
public class UserDetailsData implements UserDetails {

  private final User user;

  public UserDetailsData(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  public String getKey() {
    return user.getKey();
  }

  public String getEmail() {
    return user.getEmail();
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return MessageFormat.format(
      "{'id': '{0}', 'key': '{1}', 'username': '{2}', 'email': '{3}'}",
      user.getId(),
      user.getKey(),
      user.getUsername(),
      user.getEmail()
    );
  }
}