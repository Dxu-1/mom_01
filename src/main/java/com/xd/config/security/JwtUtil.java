package com.xd.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *@author xd
 *@create 2021/12/24
 *@description
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     *  根据userDetails获取token
     * @param userDetails 用户信息
     * @return token
     */
    public String getToken(UserDetails userDetails){
        String username = userDetails.getUsername();
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(getExpiration(expiration))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token token
     * @return 用户名
     */
    public String getUsernameFromToken(String token){
        String username = null;
        try {
            Claims claim = getClaimsFromToken(token);
            username = claim.getSubject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return username;
    }

    /**
     * 从token中获取载荷
     * @param token token
     * @return 载荷
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;

        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  claims;
    }

    /**
     *  根据userDetails和token判断token是否无效
     * @param token token
     * @param userDetails userDetails
     * @return boolean
     */
    public boolean validateToken(UserDetails userDetails,String token){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !tokenExpired(token);
    }


    public boolean tokenExpired(String token){
        Date date = getClaimsFromToken(token).getExpiration();
        return date.before(new Date());
    }

    /**
     *  获取过期时间
     * @param expiration 过期时长
     * @return 过期时间
     */
    private Date getExpiration(Long expiration) {
        return new Date(System.currentTimeMillis()+expiration*1000);
    }
}
