package com.example.chatproject;

import com.example.chatproject.Service.impl.UserServiceImpl;
import com.example.chatproject.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatProjectApplicationTests {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	void contextLoads() {
		System.out.println(userServiceImpl.findByUsername("cc").toString());;
	}

}
