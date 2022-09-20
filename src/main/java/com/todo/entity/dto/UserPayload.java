package com.todo.entity.dto;

import com.todo.annotation.EmailValidate;
import com.todo.entity.User;
import com.todo.utils.EncryptionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserPayload(
  @Size(min = 10, max = 10)
  @NotBlank
  String key,

  @Size(max = 100)
  @NotBlank
  String username,

  @NotBlank
  @Size(max = 100)
  @EmailValidate
  String email,

  @Size(min = 6)
  @NotBlank
  String password
) implements EntityDTO<User> {

  @Override
  public User toEntity() {
    return new User(
      null,
      this.key,
      this.username,
      this.email,
      EncryptionUtils.encrypt(this.password)
    );
  }
}