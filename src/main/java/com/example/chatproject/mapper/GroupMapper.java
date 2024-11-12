package com.example.chatproject.mapper;

import com.example.chatproject.pojo.ChatGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface GroupMapper {

    @Insert("insert into chatgroup(id,name,message,owner) values(#{id},#{name},#{message},#{owner})")
    void insert(ChatGroup group);

    @Select("select * from chatgroup where id=#{id}")
    ChatGroup findById(long id);

    @Select("select * from chatgroup where name=#{name}")
    ArrayList<ChatGroup> findByName(String name);

    @Select("select * from chatgroup")
    ArrayList<ChatGroup> findAll();

    @Update("update chatgroup set name=#{name},message=#{message} where id=#{id} and owner=#{owner}")
    void update(ChatGroup group);
}
