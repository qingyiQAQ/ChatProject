package com.example.chatproject.mapper;

import com.example.chatproject.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into user(id,username,password,message) values(#{id},#{username},#{password},#{message})")
    void insert(User user);
}
