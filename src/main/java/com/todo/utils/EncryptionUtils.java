package com.todo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionUtils {
  private static BCryptPasswordEncoder encryptor = null;

  public static PasswordEncoder encoder() {
    if (encryptor == null) {
      encryptor = new BCryptPasswordEncoder();
    }

    return encryptor;
  }

  public static String encrypt(String password) {
    return encoder().encode(password);
  }

  public static Boolean validation(String password, String encryptedPassword) {
    return encoder().matches(password, encryptedPassword);
  }
}