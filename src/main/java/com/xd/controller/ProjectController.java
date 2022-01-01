package com.xd.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.xd.pojo.Employee;
import com.xd.utils.JsonResult;
import com.xd.pojo.Manager;
import com.xd.pojo.Project;
import com.xd.service.IEmployeeService;
import com.xd.service.IManagerService;
import com.xd.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
@RestController
@Api(tags = "项目管理controller")
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IManagerService managerService;

    @Autowired
    private IEmployeeService employeeService;


    @GetMapping("/pro")
    @ApiOperation("获取所有的项目")
    public JsonResult getProject(@RequestParam(required = false) String type){
        return projectService.getProjects(type);
    }

    @ApiOperation("获取单个项目的所有信息")
    @GetMapping("/pro/{id}")
    public JsonResult getProById(@PathVariable("id") Integer id){
        return projectService.getProById(id);
    }

    @PostMapping("/pro")
    @ApiOperation("添加项目")
    public JsonResult addProject(@RequestBody Project project){
        project.setId(null);
        project.setCreateTime(LocalDate.now());
        project.setEndTime(null);
        project.setUpdateTime(null);
        project.setIsenable(null);
        System.out.println(project);

        if (project.getMid1() !=null){
            Manager manager1 = managerService.getById(project.getMid1());
            if (manager1 == null){
                return JsonResult.error("经理1不存在");
            }
        }
        if (project.getMid2() != null){
            Manager manager2 = managerService.getById(project.getMid2());
            if (manager2 == null){
                return JsonResult.error("经理2不存在");
            }
        }
        if (projectService.save(project)){
            return JsonResult.success("添加成功");
        }
        return JsonResult.error("添加失败");
    }

    @PutMapping("/pro")
    @ApiOperation("更新项目")
    public JsonResult update(@RequestBody Project project){
        project.setCreateTime(null);
        project.setUpdateTime(LocalDate.now());

        if (project.getMid1() !=null){
            Manager manager1 = managerService.getById(project.getMid1());
            if (manager1 == null){
                return JsonResult.error("经理1不存在");
            }
        }
        if (project.getMid2() != null){
            Manager manager2 = managerService.getById(project.getMid2());
            if (manager2 == null){
                return JsonResult.error("经理2不存在");
            }
        }

        if (projectService.updateById(project)){
            return JsonResult.success("更新成功");
        }
        return JsonResult.error("更新失败");
    }

    @DeleteMapping("/pro")
    @ApiOperation("删除项目")
    public JsonResult delete(Integer id){
        return projectService.deleteProject(id);
    }

    @GetMapping("/pro/emp")
    @ApiOperation("获取项目下员工")
    public JsonResult getProjectEmp(Integer id,@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer pageSize){

        return employeeService.getProjectEmp(id,currentPage,pageSize);
    }

    @GetMapping("/pro/export")
    @ApiOperation("excel")
    public void getProjectEmp(Integer id,String date, HttpServletResponse response){
        Project project = projectService.getById(id);

        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeParseException e){
            try {
                response.getWriter().write("日期格式不对");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        int month = localDate != null ? localDate.getMonthValue() : 0;

        String  title =localDate.getYear()+"年"+month+"月"+project.getName();

        List<Employee> list = projectService.selectEmpByMonth(localDate,id);

        ExportParams exportParams = new ExportParams(title, "", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,Employee.class,list);

        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            response.setHeader("content-type","application/octet-stream");
            response.setHeader("content-disposition","attachment;filename="+
                    URLEncoder.encode(title+".xls","UTF-8"));
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }


    @ApiOperation("添加员工到项目")
    @PostMapping("/pro/emp")
    public JsonResult addProjectEmp(Integer id ,Integer[] eIds){
        return  employeeService.updateProjectByIds(id,eIds);
    }

    @ApiOperation("删除项目下员工")
    @DeleteMapping("/pro/emp")
    public JsonResult deleteProjectEmp(Integer id ,Integer[] eIds){
        System.out.println(eIds[0]);
        return employeeService.updateProjectByIds(id,eIds,0);
    }

}
