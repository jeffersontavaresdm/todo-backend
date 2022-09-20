package com.todo.entity;

import com.todo.entity.dto.TODODTO;
import lombok.Getter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity(name = "todo")
@Getter
public class TODO {

  public TODO() {}

  public TODO(User user, String name, OffsetDateTime startDate, OffsetDateTime endDate) {
    this.user = user;
    this.name = name;
    this.startDate = Objects.requireNonNullElseGet(startDate, OffsetDateTime::now);
    this.endDate = Objects.requireNonNullElseGet(endDate, () -> OffsetDateTime.now().plusDays(1));
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  private String name;
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;

  public TODODTO toDTO() {
    return new TODODTO(
      this.id,
      this.name,
      this.startDate,
      this.endDate
    );
  }

  public TODO copy(
    String name,
    OffsetDateTime startDate,
    OffsetDateTime endDate
  ) {
    if (name != null) this.name = name;
    if (startDate != null) this.startDate = startDate;
    if (endDate != null) this.endDate = endDate;

    return this;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof TODO toDo)) return false;

    return getId().equals(toDo.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}