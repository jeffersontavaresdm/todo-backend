package com.todo.controller;

import com.todo.entity.dto.UserPayload;
import com.todo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/list")
  public ResponseEntity<?> list() {
    return ResponseEntity
      .status(HttpStatus.OK)
      .body(userService.list());
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> signup(
    @Validated
    @RequestBody
    UserPayload userPayload
  ) {
    return userService.signup(userPayload);
  }
}