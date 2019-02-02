package com.example.binarplus.web02.security;

import com.example.binarplus.web02.domain.Pengguna;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.binarplus.web02.security.SecurityConstant.EXPIRATION_TIME;
import static com.example.binarplus.web02.security.SecurityConstant.SECRET;

@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication){
        Pengguna pengguna = (Pengguna)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(pengguna.getId());

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", (Long.toString(pengguna.getId())));
        claims.put("username", pengguna.getUsername());
        claims.put("password", pengguna.getPassword());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid Jwt Sixnatue");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid Jwt Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupport");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT Claims");
        }
        return false;
    }
    public Long getUseridfromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
