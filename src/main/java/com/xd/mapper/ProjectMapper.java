package com.xd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pojo.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> getProjects(String type);

    Project getProById(@Param("id") Integer id);
}
