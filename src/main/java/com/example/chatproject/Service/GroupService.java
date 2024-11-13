package com.example.chatproject.Service;

import com.example.chatproject.pojo.Group;
import com.example.chatproject.pojo.GroupAndBool;

import java.util.ArrayList;

public interface GroupService {
    void insert(Group group);

    Group findById(long id);

    ArrayList<Group> findByName(String name);

    ArrayList<GroupAndBool> findAll(long userId);

    void update(Group group);

    void join(long id,long userId, long groupId);

    void quit(long userId, long groupId);

    boolean isJoined(long userId, long groupId);
}
