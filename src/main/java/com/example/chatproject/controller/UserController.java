package com.example.chatproject.controller;

import com.example.chatproject.Service.UserService;
import com.example.chatproject.pojo.Result;
import com.example.chatproject.pojo.User;
import com.example.chatproject.utils.JwtUtil;
import com.example.chatproject.utils.SnowFlakeUtil;
import com.example.chatproject.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        //根据用户名查询用户
        User user = userService.findByUsername(username);
        //判断该用户是否存在
        if(user==null){
            return Result.error("用户名或密码错误");
        }
        //判断密码是否正确 loginUser对象中的password是密文
        if(DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
            //登录成功
            String token = JwtUtil.generateToken(String.valueOf(user.getId()));
            //用户信息存入用户线程
            ThreadLocalUtil.set(user);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        //根据用户名查询用户
        User user = userService.findByUsername(username);
        if(user==null){
            password=DigestUtils.md5DigestAsHex(password.getBytes());
            userService.insert(new User(SnowFlakeUtil.nextId(),username,password));
            return Result.success();
        }
        return Result.error("用户名已占用");
    }
}
