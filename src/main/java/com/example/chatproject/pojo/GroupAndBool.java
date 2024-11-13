package com.example.chatproject.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupAndBool {
    @JsonSerialize(using = ToStringSerializer.class)
    long id;
    String name;
    String message;
    long memberNum;
    @JsonSerialize(using = ToStringSerializer.class)
    long ownerId;
    String ownerName;
    Boolean isJoined;
}
