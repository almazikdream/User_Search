package com.example.user_search.repository;

import com.example.user_search.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUsersById(Long id);

    // Нам не нужно писать методы вручную.
    // JpaRepository уже содержит save(), findAll(), findById() и другие.
}
