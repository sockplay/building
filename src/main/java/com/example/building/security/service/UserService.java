package com.example.building.security.service;

import com.example.building.security.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
