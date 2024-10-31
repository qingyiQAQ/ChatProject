package com.example.chatproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Validated
@AllArgsConstructor
public class User implements Serializable {
    @NotNull
    int id;
    @Pattern("^\\S{5,16}$")
    String username;
    @Pattern("^\\S{5,16}$")
    String password;
}
