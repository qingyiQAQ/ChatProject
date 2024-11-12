package com.example.chatproject.controller;

import com.example.chatproject.Service.GroupService;
import com.example.chatproject.pojo.ChatGroup;
import com.example.chatproject.pojo.Result;
import com.example.chatproject.pojo.UserMessage;
import com.example.chatproject.utils.SnowFlakeUtil;
import com.example.chatproject.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @PostMapping("/create")
    public Result createGroup(@Pattern(regexp = "^\\S{3,16}$")String name,String message){
        groupService.insert(new ChatGroup(SnowFlakeUtil.nextId(),name,message, ((UserMessage)ThreadLocalUtil.get()).getName()));
        return Result.success();
    }

    @GetMapping("/findById")
    public Result<ChatGroup> findById(long id){
        ChatGroup group = groupService.findById(id);
        if(group==null)return Result.error("找不到该群组");
        return Result.success(group);
    }

    @GetMapping("/findByName")
    public Result<ArrayList<ChatGroup>> findById(String name){
        return Result.success(groupService.findByName(name));
    }

    @GetMapping("/findAll")
    public Result<ArrayList<ChatGroup>> findAll(){
        return Result.success(groupService.findAll());
    }

    @PutMapping("/update")
    public Result updateGroup(@RequestParam long id,@Pattern(regexp = "^\\S{3,16}$") String name,String message){
        groupService.update(new ChatGroup(id,name,message,((UserMessage)ThreadLocalUtil.get()).getName()));
        return Result.success();
    }
}
