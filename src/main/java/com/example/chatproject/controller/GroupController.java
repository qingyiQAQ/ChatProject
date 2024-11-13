package com.example.chatproject.controller;

import com.example.chatproject.Service.GroupService;
import com.example.chatproject.pojo.*;
import com.example.chatproject.utils.SnowFlakeUtil;
import com.example.chatproject.utils.ThreadLocalUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //当前用户创建一个新的群组
    @PostMapping("/create")
    public Result createGroup(@Pattern(regexp = "^\\S{3,16}$")String name,String message){
        //雪花算法获得新群组id
        long newGroupId = SnowFlakeUtil.nextId();
        //插入群组表
        groupService.insert(new Group(newGroupId,name,message,1L, ((UserMessage)ThreadLocalUtil.get()).getId(), ((UserMessage)ThreadLocalUtil.get()).getName()));
        //将当前用户插入群组成员表
        groupService.join(SnowFlakeUtil.nextId(),((UserMessage)ThreadLocalUtil.get()).getId(),newGroupId);
        return Result.success();
    }

    //通过id查询某群组
    @GetMapping("/findById")
    public Result<Group> findById(long id){
        Group group = groupService.findById(id);
        if(group==null)return Result.error("找不到该群组");
        return Result.success(group);
    }

    //通过名称查询某群组
    @GetMapping("/findByName")
    public Result<ArrayList<Group>> findById(String name){
        return Result.success(groupService.findByName(name));
    }

    //查询用户的所有群组加入情况
    @GetMapping("/findAll")
    public Result<ArrayList<GroupAndBool>> findAll(){
        return Result.success(groupService.findAll(((UserMessage)ThreadLocalUtil.get()).getId()));
    }

    //用户更新群组信息
    @PutMapping("/update")
    public Result updateGroup(
            @RequestParam @JsonSerialize(using = ToStringSerializer.class) long id,
            @Pattern(regexp = "^\\S{3,16}$") String name,
            String message){
        Group oldGroup = groupService.findById(id);
        groupService.update(new Group(id,name,message,oldGroup.getMemberNum(),((UserMessage)ThreadLocalUtil.get()).getId(),((UserMessage)ThreadLocalUtil.get()).getName()));
        return Result.success();
    }

    //用户加入群组
    @PostMapping("/join")
    public Result joinGroup(@RequestParam @JsonSerialize(using = ToStringSerializer.class) long groupId){
        //检测群组是否存在
        Group group = groupService.findById(groupId);
        if(group==null){
            return Result.error("该群组不存在");
        }
        long userId = ((UserMessage)ThreadLocalUtil.get()).getId();
        //检测是否已经加入
        if(groupService.isJoined(userId,groupId)){
            return Result.error("已经加入该群组");
        }
        groupService.join(SnowFlakeUtil.nextId(),userId,groupId);
        //成员数量+1
        group.setMemberNum(group.getMemberNum()+1L);
        groupService.update(group);
        return Result.success();
    }

    @PostMapping("/quit")
    public Result quitGroup(@RequestParam @JsonSerialize(using = ToStringSerializer.class) long groupId){
        //检测群组是否存在
        Group group = groupService.findById(groupId);
        if(group==null){
            return Result.error("该群组不存在");
        }
        long userId = ((UserMessage)ThreadLocalUtil.get()).getId();
        //检测是否是群主
        if(userId==group.getOwnerId()){
            return Result.error("您是群主，无法退出群聊");
        }
        //检测是否已经加入
        if(!groupService.isJoined(userId,groupId)){
            return Result.error("还未加入该群组");
        }
        groupService.quit(userId,groupId);
        //成员数量-1
        group.setMemberNum(group.getMemberNum()-1L);
        groupService.update(group);
        return Result.success();
    }
}
