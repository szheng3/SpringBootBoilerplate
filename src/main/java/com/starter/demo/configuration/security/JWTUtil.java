package com.starter.demo.configuration.security;

import com.baomidou.mybatisplus.extension.api.R;
import com.starter.demo.domain.User;
import com.starter.demo.domain.generated.Role;
import com.starter.demo.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    private Key key;

    public JWTUtil(@Value("${springbootwebfluxjjwt.jjwt.secret}") String secret) {
        this.key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }



    @Value("${springbootwebfluxjjwt.jjwt.expiration}")
    private String expirationTime;

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        List<RoleEnum> roles = user.getRoleEnums().stream().map(Role::getRole).collect(Collectors.toList());
        claims.put("role", roles);
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        Long expirationTimeLong = Long.parseLong(expirationTime); //in second

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

}
