package com.example.chatproject.controller;

import com.example.chatproject.Service.UserService;
import com.example.chatproject.pojo.Result;
import com.example.chatproject.pojo.User;
import com.example.chatproject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public Result<String> login(String username, String password){
        //根据用户名查询用户
        User user = userService.findByUsername(username);
        //判断该用户是否存在
        if(user==null){
            return Result.error("用户名或密码错误");
        }
        //判断密码是否正确 loginUser对象中的password是密文
        if(DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            //登录成功
            String token = JwtUtil.generateToken(username);
            //把token存储到redis中
            stringRedisTemplate.opsForValue().set("token:"+token,token,2, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }
}
