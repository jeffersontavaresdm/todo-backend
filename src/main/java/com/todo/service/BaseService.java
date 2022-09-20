package com.todo.service;

import com.todo.exception.base.NotFoundException;
import com.todo.repository.BaseRepository;

import java.util.Optional;

public abstract class BaseService<T> {
  final BaseRepository<T> repository;

  public BaseService(BaseRepository<T> repository) {
    this.repository = repository;
  }

  public T findByIdOrNull(Long id) {
    return repository.findById(id).orElse(null);
  }

  public T findByIdOrThrowNotFoundException(Long id) {
    Optional<T> entity = repository.findById(id);

    if (entity.isPresent()) {
      return entity.get();
    } else {
      throw new NotFoundException();
    }
  }
}