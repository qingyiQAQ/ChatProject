package com.example.chatproject.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Group implements Serializable {
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    long id;
    @Pattern(regexp = "^\\S{5,16}$")
    String name;
    String message;
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    long memberNum;
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    long ownerId;
    @NotNull
    String ownerName;
}
