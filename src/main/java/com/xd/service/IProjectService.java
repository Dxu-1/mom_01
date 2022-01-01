package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pojo.Employee;
import com.xd.utils.JsonResult;
import com.xd.pojo.Project;

import java.time.LocalDate;
import java.util.List;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
public interface IProjectService extends IService<Project> {

    JsonResult getProjects(String type);

    JsonResult deleteProject(Integer id);

    JsonResult getProById(Integer id);

    List<Employee> selectEmpByMonth(LocalDate localDate, Integer id);
}
