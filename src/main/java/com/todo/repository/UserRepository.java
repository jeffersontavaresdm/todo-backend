package com.todo.repository;

import com.todo.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
  Optional<User> findByEmail(String email);

  Optional<User> findByKeyAndEmail(String key, String email);
}