package com.todo.controller;

import com.todo.auth.dto.TODOUserDetails;
import com.todo.entity.dto.TODOPayload;
import com.todo.service.TODOService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class TODOController {

  private final TODOService todoService;

  @PostMapping("/create")
  public ResponseEntity<?> create(
    @Validated @RequestBody TODOPayload toDoPayload,
    @AuthenticationPrincipal String principal
  ) {
    return todoService.create(toDoPayload, null);
  }

  @GetMapping("/list")
  public ResponseEntity<?> list() {
    return todoService.findAll();
  }

  @GetMapping("/get-by-id")
  public ResponseEntity<?> getById(@RequestParam Long id) {
    return todoService.getById(id);
  }

  @GetMapping("/get-by-name")
  public ResponseEntity<?> getByName(@RequestParam String name) {
    return todoService.getByName(name);
  }

  //  @Secured("ADMIM")
  @PutMapping("/update")
  public ResponseEntity<?> update(@Valid @RequestBody TODOPayload toDoPayload, @RequestParam Long id) {
    return todoService.update(toDoPayload, id);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestParam Long id) {
    return todoService.delete(id);
  }
}