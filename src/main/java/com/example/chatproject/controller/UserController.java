package com.example.chatproject.controller;

import com.example.chatproject.Service.UserService;
import com.example.chatproject.pojo.Result;
import com.example.chatproject.pojo.User;
import com.example.chatproject.utils.Md5Util;
//import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result<String> login(String username, String password){
//        //根据用户名查询用户
//        User loginUser = userService.findByUsername(username);
//        //判断该用户是否存在
//        if(loginUser==null){
//            return Result.error("用户名或密码错误");
//        }
//        //判断密码是否正确 loginUser对象中的password是密文
//        if(Md5Util.md5(password).equals(loginUser.getPassword())){
//            //登录成功
//            Map<String,Object> claims = new HashMap<>();
//            claims.put("id",loginUser.getId());
//            claims.put("username",loginUser.getUsername());
//            String token = JwtUtil.genToken(claims);
//            //把token存储到redis中
//            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//            operations.set(token,token,1, TimeUnit.HOURS);
//            return Result.success(token);
//        }

        return Result.error("用户名或密码错误");
    }
}
