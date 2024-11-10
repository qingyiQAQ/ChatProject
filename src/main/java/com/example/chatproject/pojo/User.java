package com.example.chatproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements Serializable {
    @NotNull
    long id;
    @Pattern(regexp = "^\\S{5,16}$")
    String username;
    @Pattern(regexp = "^\\S{5,16}$")
    String password;
}
