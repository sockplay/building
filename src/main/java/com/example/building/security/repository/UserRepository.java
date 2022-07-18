package com.example.building.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.building.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
