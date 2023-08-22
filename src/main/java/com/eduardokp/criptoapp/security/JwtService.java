package com.eduardokp.criptoapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private int expirationInMinutes;

    @Value("${security.jwt.key}")
    private String key;

    public String generateToken(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationInMinutes);

        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(calendar.getTime())
                .signWith(secretKey)
                .compact();
    }


    public String getUsername(String token) {
       return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        SecretKey secretKey = getSecretKey();

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }
}
