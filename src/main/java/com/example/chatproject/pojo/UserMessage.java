package com.example.chatproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserMessage {
    long id;
    String name;
    String message;
}