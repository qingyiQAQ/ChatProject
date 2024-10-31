package com.example.chatproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String KEY = "chencheng";
	
	//接收业务数据,生成token并返回
    public static String generateToken(String s) {
        return Jwts.builder()
                .setSubject(s)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时后过期
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

	//接收token,验证token,并返回业务数据
    public static String validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
