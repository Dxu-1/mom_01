package com.xd.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.utils.JsonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
*@author xd
*@create 2021/12/25
*@description
*/
// 认证失败的处理类
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        // 设置返回内容
        JsonResult result = JsonResult.error("尚未登录！");
        result.setCode(401);

        // 设置返回数据类型为json 以及编码
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 获取输出流将内容返回出去
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.flush();

    }
}
