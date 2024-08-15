package com.society.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import java.util.Base64;

@Component
public class JwtUtil {
 
    @Value("${jwt.secret}")
    private String secretKey;
 
    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;
 
    private SecretKey key;
 
    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("The secret key must be at least 256 bits (32 bytes) long.");
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
 
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
 

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key) // ensure you are using the correct key
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) ;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
