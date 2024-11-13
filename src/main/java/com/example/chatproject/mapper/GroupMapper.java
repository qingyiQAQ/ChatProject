package com.example.chatproject.mapper;

import com.example.chatproject.pojo.Group;
import com.example.chatproject.pojo.GroupAndBool;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface GroupMapper {

    @Insert("insert into chatgroup(id,name,message,member_num,owner_id,owner_name) values(#{id},#{name},#{message},#{memberNum},#{ownerId},#{ownerName})")
    void insert(Group group);

    @Select("select * from chatgroup where id=#{id}")
    Group findById(long id);

    @Select("select * from chatgroup where name=#{name}")
    ArrayList<Group> findByName(String name);

    //查找所有加入的群组和未加入的群组
    @Select("""
            SELECT c.id,c.name,c.message,c.member_num,c.owner_id,c.owner_name,
                   CASE
                       WHEN g.user_id IS NOT NULL THEN 1
                       ELSE 0
                   END AS is_joined
            FROM chatgroup c
            LEFT JOIN groupmember g ON c.id = g.chatgroup_id AND g.user_id = #{id};
            """)
    ArrayList<GroupAndBool> findAll(long id);

    @Update("update chatgroup set name=#{name},message=#{message},member_num=#{memberNum} where id=#{id} and owner_id=#{ownerId}")
    void update(Group group);

    //加入群组
    @Insert("insert into groupmember(id,user_id,chatgroup_id) values(#{id},#{userId},#{groupId})")
    void join(long id,long userId,long groupId);

    @Delete("delete from groupmember where user_id=#{userId} and chatgroup_id=#{groupId}")
    void quit(long userId, long groupId);

    //查找是否加入了某群组
    @Select("""
            SELECT EXISTS (
                SELECT 1
                FROM groupmember g
                WHERE g.user_id = #{userId}
                AND g.chatgroup_id = #{groupId}
            ) AS isJoined;
            """)
    boolean isJoined(long userId,long groupId);
}
