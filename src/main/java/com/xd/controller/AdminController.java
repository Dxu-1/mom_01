package com.xd.controller;


import com.xd.pojo.Admin;
import com.xd.pojo.JsonResult;
import com.xd.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
@RestController
@Api(tags = "登录controller")
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAdminService adminService;



    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JsonResult login(@RequestBody Admin admin){

        return adminService.login(admin.getUsername(),admin.getPassword());
    }

    @GetMapping("/test")
    @ApiOperation("登录成功测试")
    public JsonResult test(){
        return JsonResult.success("啊对对对");
    }





//    @GetMapping("/register")
    public String register(){
        Admin admin = new Admin(1, "admin", passwordEncoder.encode("123"));
//        adminService.save(admin);
        return "";
    }
}
