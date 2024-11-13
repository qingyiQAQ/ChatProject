package com.example.chatproject.Service.impl;

import com.example.chatproject.Service.GroupService;
import com.example.chatproject.mapper.GroupMapper;
import com.example.chatproject.pojo.Group;
import com.example.chatproject.pojo.GroupAndBool;
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
    public void insert(Group group) {
        groupMapper.insert(group);
    }

    @Override
    public Group findById(long id) {
        if(Boolean.TRUE.equals(redisTemplate.hasKey("group:"+id))){
            return (Group)redisTemplate.opsForValue().get("group:"+id);
        }
        else{
            Group group = groupMapper.findById(id);
            if(group!=null)redisTemplate.opsForValue().set("group:"+id,group,10, TimeUnit.HOURS);
            return group;
        }
    }

    @Override
    public ArrayList<Group> findByName(String name) {
        return groupMapper.findByName(name);
    }

    @Override
    public ArrayList<GroupAndBool> findAll(long id) {
        return groupMapper.findAll(id);
    }

    @Override
    synchronized public void update(Group group) {
        redisTemplate.opsForValue().set("group:"+group.getId(),group);
        groupMapper.update(group);
    }

    @Override
    public void join(long id,long userId, long groupId) {
        groupMapper.join(id,userId,groupId);
    }

    @Override
    public void quit(long userId, long groupId) {
        groupMapper.quit(userId,groupId);
    }

    @Override
    public boolean isJoined(long userId, long groupId) {
        return groupMapper.isJoined(userId,groupId);
    }
}
