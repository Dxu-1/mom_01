package com.xd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.mapper.ManagerMapper;
import com.xd.pojo.Manager;
import com.xd.service.IManagerService;
import org.springframework.stereotype.Service;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
    
}
