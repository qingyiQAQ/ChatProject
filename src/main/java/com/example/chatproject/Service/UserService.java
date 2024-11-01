package com.example.chatproject.Service;

import com.example.chatproject.pojo.User;

public interface UserService {
    User findByUsername(String username);
    void insert(User user);
}
