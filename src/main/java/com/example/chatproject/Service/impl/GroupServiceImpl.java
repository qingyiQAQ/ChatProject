package com.example.chatproject.Service.impl;

import com.example.chatproject.Service.GroupService;
import com.example.chatproject.mapper.GroupMapper;
import com.example.chatproject.pojo.ChatGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void insert(ChatGroup group) {
        groupMapper.insert(group);
    }

    @Override
    public ChatGroup findById(long id) {
        if(Boolean.TRUE.equals(redisTemplate.hasKey("group:"+id))){
            return (ChatGroup)redisTemplate.opsForValue().get("group:"+id);
        }
        else{
            ChatGroup group = groupMapper.findById(id);
            if(group!=null)redisTemplate.opsForValue().set("group:"+id,group,10, TimeUnit.HOURS);
            return group;
        }
    }

    @Override
    public ArrayList<ChatGroup> findByName(String name) {
        return groupMapper.findByName(name);
    }

    @Override
    public ArrayList<ChatGroup> findAll() {
        return groupMapper.findAll();
    }

    @Override
    synchronized public void update(ChatGroup group) {
        if(Boolean.TRUE.equals(redisTemplate.hasKey("group:"+group.getId()))){
            redisTemplate.opsForValue().set("group:"+group.getId(),group);
        }
        groupMapper.update(group);
    }
}
