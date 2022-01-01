package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pojo.JsonResult;
import com.xd.pojo.Project;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
public interface IProjectService extends IService<Project> {

    JsonResult getProjects(String type);

    JsonResult deleteProject(Integer id);

    JsonResult getProById(Integer id);
}
