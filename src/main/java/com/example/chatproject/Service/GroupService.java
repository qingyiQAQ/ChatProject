package com.example.chatproject.Service;

import com.example.chatproject.pojo.ChatGroup;

import java.util.ArrayList;

public interface GroupService {
    void insert(ChatGroup group);

    ChatGroup findById(long id);

    ArrayList<ChatGroup> findByName(String name);

    ArrayList<ChatGroup> findAll();

    void update(ChatGroup chatGroup);
}
