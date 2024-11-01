package com.example.chatproject;

import com.example.chatproject.Service.UserService;
import com.example.chatproject.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatProjectApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		JwtUtil.generateToken("111111");
	}

}
