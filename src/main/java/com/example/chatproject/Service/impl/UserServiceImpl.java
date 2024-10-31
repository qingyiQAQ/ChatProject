package com.example.chatproject.Service.impl;

import com.example.chatproject.Service.UserService;
import com.example.chatproject.mapper.UserMapper;
import com.example.chatproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User findByUsername(String username){
        if(Boolean.TRUE.equals(stringRedisTemplate.hasKey("user:"+username))){
            return stringRedisTemplate.opsForValue().get("user:"+username);
        }
        else{
            User user = userMapper.findByUserName(username);
            stringRedisTemplate.opsForHash().putAll("user:"+username, (Map<?, ?>) user);
            return user;
        }
    }

}
