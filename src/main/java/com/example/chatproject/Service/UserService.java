package com.example.chatproject.Service;

import com.example.chatproject.mapper.UserMapper;
import com.example.chatproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);
}
