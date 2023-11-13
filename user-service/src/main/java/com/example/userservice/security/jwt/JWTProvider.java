package com.example.userservice.security.jwt;

import com.example.userservice.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTProvider {

    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    @Value("${jwt.access-token-validity-in-seconds}")
    private long accessTokenValidTime;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    public long refreshTokenValidTime;

    public static final String jwtType = "Bearer ";

    private final RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    protected void init() {
        String encodeKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodeKey.getBytes());
    }

    public String generateAccessToken(String userId, List<Object> roles) {
        Date now = new Date();

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", roles.get(0).toString());

        return Jwts.builder()
                .setSubject("access")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(String userId) {
        Date now = new Date();

        Claims claims = Jwts.claims().setSubject(userId);

        String refreshToken = Jwts.builder()
                .setSubject("refresh")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        // redis에 refresh 토큰 저장
        /*
        redisTemplate.opsForValue().set(
                userId,
                refreshToken,
                refreshTokenValidTime,
                TimeUnit.MILLISECONDS
        );
        */

        return refreshToken;
    }
}
