package com.xd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.config.security.JwtUtil;
import com.xd.mapper.AdminMapper;
import com.xd.pojo.Admin;
import com.xd.pojo.JsonResult;
import com.xd.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
*@author xd
*@create 2021/12/26
*@description 
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper,Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Admin getAdminByAdminName(String username) {
        return adminMapper.getAdminByAdminName(username);
    }

    @Override
    public JsonResult login(String username, String password) {

        if (null == username){
            return JsonResult.error("用户名不正确");
        }

        if (null == password){
            return JsonResult.error("密码不正确");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return JsonResult.error("账号或密码不正确");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        HashMap<String, String> tokenMap = new HashMap<>();

        tokenMap.put("token",jwtUtil.getToken(userDetails));
        tokenMap.put("tokenHead",tokenHead);
        return JsonResult.success("登录成功",tokenMap);
    }


}
