package com.xd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.mapper.EmployeeMapper;
import com.xd.mapper.ProjectMapper;
import com.xd.pojo.Employee;
import com.xd.pojo.JsonResult;
import com.xd.pojo.Project;
import com.xd.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


/**
*@author xd
*@create 2021/12/26
*@description 
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public JsonResult getProjects(String type) {

        List<Project> projects =  projectMapper.getProjects(type);
        if (projects == null){
            if (type ==null){
                return JsonResult.selectError();
            }else {
                return JsonResult.success("该类别下没有项目");
            }
        }
        return JsonResult.selectSuccess(projects);
    }

    @Override
    @Transactional
    public JsonResult deleteProject(Integer id) {
        Project project = new Project();
        project.setId(id);
        project.setIsenable(1);
        project.setEndTime(LocalDate.now());

        List<Employee> projectEmp = employeeMapper.selectList(new QueryWrapper<Employee>().eq("pro_id", id).eq("isdelete",0));
        Integer[] eids = null;
        if (projectEmp != null){
            eids = projectEmp.stream().map(Employee::getId).toArray(Integer[]::new);
            employeeMapper.updateProject(null, eids, 0);
        }

        if (1 == projectMapper.updateById(project)){
            return JsonResult.deleteSuccess();
        }
        return JsonResult.deleteError();
    }

    @Override
    public JsonResult getProById(Integer id) {
        Project project = projectMapper.getProById(id);
        return JsonResult.selectSuccess(project);
    }

}
