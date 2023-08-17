package com.example.demo.security.config;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ResponseDTO;
import com.example.demo.DTO.UserInfo;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.SecurityConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final UserRepository userRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public UserInfo extractUserInfos(String token) {
        return extractClaim(token, (claims) -> (UserInfo) claims.get("userInfos"));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> hashMap = new HashMap<>();

        User user = userRepository.getUserInfo(userDetails.getUsername());

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setFirstname(user.getFirstname());
        userInfo.setLastname(user.getLastname());
        userInfo.setEmail(user.getEmail());
        userInfo.setRole(user.getRole().toString());

        hashMap.put("userInfos", userInfo);
        return generateToken(hashMap, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.ACCESS_TOKEN_VALIDITY_SECONDS))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SecurityConstant.SIGNING_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void handleTokenException(HttpServletResponse res, HttpStatus status, String message) {
        try {
            res.setStatus(status.value());
            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writeValueAsString(new ResponseDTO(status.value() + "", message, null));
            res.setContentType("application/json");
            res.getWriter().write(response);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
