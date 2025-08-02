package com.example.tarun.Utility;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
    public class JwtUtil {

        private final String SECRET = "d4b2e1f4a6c781f9c1a0f6d9e5c37a89";

        public String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                    .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                    .compact();
        }

        public String extractUsername(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            return extractUsername(token).equals(userDetails.getUsername());
        }
    }
