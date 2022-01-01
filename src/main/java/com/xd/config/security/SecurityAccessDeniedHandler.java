package com.xd.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.utils.JsonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        // 设置返回内容
        JsonResult result = JsonResult.error("权限不足！");
        result.setCode(403);

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
