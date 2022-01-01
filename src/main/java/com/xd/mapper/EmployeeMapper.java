package com.xd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pojo.Employee;
import com.xd.pojo.EmployeeRequestInfo;
import com.xd.pojo.JsonResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*@author xd
*@create 2021/12/26
*@description
*/
public interface EmployeeMapper extends BaseMapper<Employee> {



    IPage<Employee> getEmployees(Page<Employee> page, @Param("info") EmployeeRequestInfo info);

    Integer updateProject(@Param("id") Integer id, @Param("eIds") Integer[] eIds, @Param("iswork") Integer iswork);
}
