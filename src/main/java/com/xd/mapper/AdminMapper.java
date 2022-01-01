package com.xd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pojo.Admin;

/**
*@author xd
*@create 2021/12/26
*@description 
*/

public interface AdminMapper extends BaseMapper<Admin> {

    Admin getAdminByAdminName(String username);
}
