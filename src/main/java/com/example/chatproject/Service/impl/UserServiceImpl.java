package com.example.chatproject.Service.impl;

import com.example.chatproject.Service.UserService;
import com.example.chatproject.mapper.UserMapper;
import com.example.chatproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User findByUsername(String username){
        if(Boolean.TRUE.equals(redisTemplate.hasKey("user:"+username))){
            return (User)redisTemplate.opsForValue().get("user:"+username);
        }
        else{
            User user = userMapper.findByUserName(username);
            if(user!=null)redisTemplate.opsForValue().set("user:"+username,user,10, TimeUnit.HOURS);
            return user;
        }
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

}
