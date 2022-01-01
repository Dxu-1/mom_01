package com.xd.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
*@author xd
*@create 2021/12/25
*@description
*/

// jwt登录过滤器
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader; // JWT 存储的请求头( key )
    @Value("${jwt.tokenHead}")
    private String tokenHead; // JWT 负载中拿到开头( value )

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // 获取token请求头
        String authHeader = request.getHeader(tokenHeader);
        // 判断内容开头是否正确
        if (null !=authHeader && authHeader.startsWith(tokenHead)){
            // 截取到token
            String token = authHeader.substring(tokenHead.length());
            // 获取用户名
            String username = jwtUtil.getUsernameFromToken(token);
            // 判断是否已经登录过，
            // 登陆过后SecurityContextHolder.getContext().getAuthentication() 不为空
            if (null != username && null== SecurityContextHolder.getContext().getAuthentication()){
                // 登录 拿到userDetails
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 判断token是否有效
                if (jwtUtil.validateToken(userDetails,token)){

                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }


            }
        }
        filterChain.doFilter(request,response);
    }
}
