package com.todo.controller;

import com.todo.entity.dto.UserPayload;
import com.todo.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DummyController {

  private final Logger logger = LoggerUtils.loggerFor(this);

  @GetMapping("/email-validation")
  public void emailValidation(@RequestBody @Validated UserPayload userPayload) {
    logger.info(userPayload.toString());
  }
}