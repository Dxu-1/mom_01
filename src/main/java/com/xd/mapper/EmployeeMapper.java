package com.xd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.pojo.Employee;
import com.xd.pojo.EmployeeRequestInfo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
*@author xd
*@create 2021/12/26
*@description
*/
public interface EmployeeMapper extends BaseMapper<Employee> {



    IPage<Employee> getEmployees(Page<Employee> page, @Param("info") EmployeeRequestInfo info);

    Integer updateProject(@Param("id") Integer id, @Param("eIds") Integer[] eIds, @Param("iswork") Integer iswork);

    List<Employee> selectProjectEmpByMonth(@Param("localDate") LocalDate localDate, @Param("id") Integer id);

    List<Integer> getEmpIds(@Param("id") Integer id);
}
