package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pojo.Employee;
import com.xd.pojo.EmployeeRequestInfo;
import com.xd.utils.JsonResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xd
 * @create 2021/12/26
 * @description
 */
public interface IEmployeeService extends IService<Employee> {

    JsonResult updateEmpById(Employee employee);

    JsonResult addEmp(Employee employee);

    JsonResult getEmployees(EmployeeRequestInfo info);

    JsonResult getProjectEmp(@Param("id") Integer id, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    JsonResult updateProjectByIds(@Param("id") Integer id, @Param("eIds") Integer[] eIds);

    JsonResult updateProjectByIds(@Param("id") Integer id, @Param("eIds") Integer[] eIds, @Param("iswork") Integer iswork);

    List<Integer> getEmpIds(Integer id);
}
