package com.todo.entity.dto;

import com.todo.entity.TODO;
import com.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class TODOPayload {
  @NotBlank
  @Size(max = 100)
  String name;
  OffsetDateTime startDate;
  OffsetDateTime endDate;

  public TODO toEntity(User user) {
    return new TODO(
      user,
      this.name,
      this.startDate,
      this.endDate
    );
  }
}