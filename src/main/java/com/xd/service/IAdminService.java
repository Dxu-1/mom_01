package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pojo.Admin;
import com.xd.utils.JsonResult;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
public interface IAdminService extends IService<Admin> {

    Admin getAdminByAdminName(String username);

    JsonResult login(String username, String password);


}
