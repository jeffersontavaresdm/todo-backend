package com.todo.repository;

import com.todo.entity.TODO;

import java.util.Optional;

public interface TODORepository extends BaseRepository<TODO> {

  Optional<TODO> getByName(String name);
}