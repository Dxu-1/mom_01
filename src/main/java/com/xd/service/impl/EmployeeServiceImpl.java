package com.xd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.mapper.EmployeeMapper;
import com.xd.mapper.ProjectMapper;
import com.xd.pojo.Employee;
import com.xd.pojo.EmployeeRequestInfo;
import com.xd.utils.JsonResult;
import com.xd.pojo.Project;
import com.xd.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
*@author xd
*@create 2021/12/26
*@description 
*/

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ProjectMapper projectMapper;


    @Override
    public JsonResult updateEmpById(Employee employee) {
        employee.setCreateTime(null);
        employee.setProId(null);
        employee.setIsdelete(null);
        employee.setUpdateTime(LocalDate.now());
        int rows = employeeMapper.updateById(employee);
        if (rows == 1){
            return JsonResult.updateSuccess();
        }
        return JsonResult.updateError();
    }

    @Override
    public JsonResult addEmp(Employee employee) {

        Employee emp = employeeMapper.selectOne(new QueryWrapper<Employee>().eq("id", employee.getRecomEmpId()));
        if (emp == null){
            return JsonResult.error("推荐人不存在");
        }
        int rows = employeeMapper.insert(employee);
        if (rows == 1){
            return JsonResult.insertSuccess();
        }
        return JsonResult.insertError();
    }

    @Override
    public JsonResult getEmployees(EmployeeRequestInfo info) {

        if (null == info){
            info = new EmployeeRequestInfo();
            info.setIsdelete(0);
            info.setCurrentPage(1);
            info.setPageSize(5);
            info.setOrder(0);
        }else{
            if (info.getIsdelete() == null){
                info.setIsdelete(0);
            }
            if (info.getCurrentPage() == null){
                info.setCurrentPage(1);
            }
            if (info.getOrder() == null){
                info.setOrder(0);
            }
        }
        info.setPageSize(6);

        Page<Employee> page = new Page<>(info.getCurrentPage(),
                                        info.getPageSize());
        IPage<Employee> employees = employeeMapper.getEmployees(page,info);

        return JsonResult.success("查询成功",employees);
    }

    @Override
    public JsonResult getProjectEmp(Integer id, Integer currentPage, Integer pageSize) {
        Page<Employee> page = new Page<>(currentPage, pageSize);

        Page<Employee> projectEmp = employeeMapper.selectPage(page, new QueryWrapper<Employee>()
                .eq("pro_id", id).eq("isdelete",0));
        return JsonResult.selectSuccess(projectEmp);
    }

    @Override
    public JsonResult updateProjectByIds(Integer id, Integer[] eIds) {

        Project project = projectMapper.selectById(id);
        if (project == null){
            return JsonResult.error("项目不存在");
        }

        List<Employee> list = employeeMapper.selectBatchIds(Arrays.asList(eIds));
        if (list.size() != eIds.length || eIds.length == 0){
            return JsonResult.error("有员工已经不存在");
        }

        Integer integer = employeeMapper.updateProject(id, eIds,null);
        if (integer == eIds.length){
            return JsonResult.updateSuccess();
        }
        return JsonResult.updateError();
    }

    @Override
    public JsonResult updateProjectByIds(Integer id, Integer[] eIds, Integer iswork) {
        Project project = projectMapper.selectById(id);
        if (project == null){
            return JsonResult.error("项目不存在");
        }

        List<Employee> list = employeeMapper.selectBatchIds(Arrays.asList(eIds));
        if (list.size() != eIds.length || eIds.length == 0){
            return JsonResult.error("有员工已经不存在");
        }

        for (Employee employee : list) {
            if (!employee.getProId().equals(id)){
                return JsonResult.error("有员工不在项目下");
            }
        }
        Integer integer = employeeMapper.updateProject(null, eIds,iswork);
        if (integer == eIds.length){
            return JsonResult.deleteSuccess();
        }
        return JsonResult.deleteError();
    }

    @Override
    public List<Integer> getEmpIds(Integer id) {
        return employeeMapper.getEmpIds(id);
    }
}
