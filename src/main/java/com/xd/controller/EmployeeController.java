package com.xd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.pojo.Employee;
import com.xd.pojo.EmployeeRequestInfo;
import com.xd.utils.JsonResult;
import com.xd.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


/**
*@author xd
*@create 2021/12/26
*@description 
*/
@RestController
@Api(tags = "员工管理类")
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;


    /**
     * 获取所有员工根据条件查询 并分页
     * @param info 条件列表
     * @return 员工列表
     */
    @GetMapping("/emp")
    @ApiOperation("获取所有员工")
    public JsonResult getEmp(EmployeeRequestInfo info){
        return employeeService.getEmployees(info);
    }


    /**
     *  添加员工信息,不准指定id，以及不能直接加入到项目
     * @param employee 员工信息
     * @return 是否修改成功
     */
    @PostMapping("/emp")
    @ApiOperation("添加员工")
    public JsonResult addEmp(@RequestBody Employee employee){
        employee.setId(null);
        employee.setProId(null);
        employee.setCreateTime(LocalDate.now());
        if (employee.getRecomEmpId() == null){
            if (!employeeService.save(employee)){
                return JsonResult.insertError();
            }else {
                return JsonResult.insertSuccess();
            }
        }else{
            return employeeService.addEmp(employee);
        }
    }

    /**
     *  更新员工信息 ,不允许修改所在项目以及创建时间
     *  可以在项目管理那边修改员工的所在项目
     * @param employee 员工信息
     * @return 是否修改成功
     */
    @PutMapping("/emp")
    @ApiOperation("更新员工")
    public JsonResult updateEmp(@RequestBody Employee employee){
        return  employeeService.updateEmpById(employee);
    }

    /**
     * 根据id删除员工
     * @param id 员工id
     * @return 是否删除成功
     */
    @DeleteMapping("/emp")
    @ApiOperation("删除员工")
    public JsonResult delete(Integer id){
        Employee employee = new Employee();
        employee.setIsdelete(1);
        employee.setProId(null);
        employee.setIswork(0);
        employee.setId(id);
        System.out.println(id);

        if (employeeService.updateById(employee)){
            return JsonResult.deleteSuccess();
        }
        return JsonResult.deleteError();
    }

    @GetMapping("/emp/{id}")
    @ApiOperation("获取单个员工的所有信息")
    public JsonResult getSingleEmp(@PathVariable("id") Integer id){
        if (null == id){
            return JsonResult.error("id不正确");
        }
        List<Employee> emp = employeeService.list(new QueryWrapper<Employee>().eq("id", id));
        if ( 0 == emp.size()){
            return JsonResult.error("没有这个员工");
        }
        return JsonResult.selectSuccess(emp);
    }

}
