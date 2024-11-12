package com.example.chatproject.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.chatproject.pojo.UserMessage;

import java.util.Date;

public class JwtUtil {

    private static final String KEY = "chencheng";
	
	//接收业务数据,生成token并返回
    public static String generateToken(UserMessage userMessage) {
        return JWT.create()
                .withClaim("id", userMessage.getId())
                .withClaim("name", userMessage.getName())
                .withClaim("message", userMessage.getMessage())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时后过期
                .sign(Algorithm.HMAC256(KEY));
    }

	//接收token,验证token,并返回业务数据
    public static UserMessage validateToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token);
        return new UserMessage(
                jwt.getClaim("id").asLong(),
                jwt.getClaim("name").asString(),
                jwt.getClaim("message").asString());
    }

}
