package com.example.chatproject.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ChatGroup implements Serializable {
    @NotNull
    long id;
    @Pattern(regexp = "^\\S{5,16}$")
    String name;
    String message;
    @NotNull
    String owner;
}
