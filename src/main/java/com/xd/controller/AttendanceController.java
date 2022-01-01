package com.xd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.pojo.Attendance;
import com.xd.utils.JsonResult;
import com.xd.pojo.Project;
import com.xd.service.IAttendanceService;
import com.xd.service.IEmployeeService;
import com.xd.service.IProjectService;
import com.xd.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
*@author xd
*@create 2021/12/27
*@description 
*/
@RestController
@RequestMapping("/api")
@Api(tags = "考勤管理")
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;


    @Autowired
    private IProjectService projectService;

    @Autowired
    private IEmployeeService employeeService;


    @ApiOperation("插入考勤信息")
    @PostMapping("/att")
    public JsonResult addAtt(@RequestBody Attendance attendance){
        return  attendanceService.addAtt(attendance);
    }

    @ApiOperation("修改考勤信息")
    @PutMapping("/att")
    public JsonResult updateAtt(@RequestBody Attendance attendance){
        return attendanceService.updateAtt(attendance);
    }

    @DeleteMapping("/att")
    @ApiOperation("删除考勤记录")
    public JsonResult updateAtt(Integer id){
        if (attendanceService.removeById(id)){
            return JsonResult.deleteSuccess();
        }
        return JsonResult.deleteError();
    }


    @ApiOperation("根据项目日期查询")
    @GetMapping("/att")
    public JsonResult getAttByProjectDate(@RequestParam(required = false)String  date ,@RequestParam Integer proId){
        if (DateUtil.stringDate(date) instanceof JsonResult){
            return (JsonResult)DateUtil.stringDate(date);
        }
        LocalDate localDate = (LocalDate) DateUtil.stringDate(date);
        return attendanceService.getProjectAttByDate(proId,localDate);
    }

    @ApiOperation("根据项目月份查询")
    @GetMapping("/att/month")
    public JsonResult getAttByProjectMonth(@RequestParam(required = false)String  date ,@RequestParam Integer proId){
        if (DateUtil.stringDate(date) instanceof JsonResult){
            return (JsonResult)DateUtil.stringDate(date);
        }
        LocalDate localDate = (LocalDate) DateUtil.stringDate(date);
        return attendanceService.getProjectAttByMonth(proId,localDate);
    }

    @ApiOperation("项目人员整体打卡")
    @GetMapping("/att/pro")
    public JsonResult addProjectAtt(Integer id,@RequestParam(required = false)String  date){
        if (DateUtil.stringDate(date) instanceof JsonResult){
            return (JsonResult)DateUtil.stringDate(date);
        }
        LocalDate localDate = (LocalDate) DateUtil.stringDate(date);

        List<Attendance> atts = attendanceService.list(new QueryWrapper<Attendance>().eq("work_date", localDate));
        if (atts.size() != 0){
            return JsonResult.error("已经有人考勤过了");
        }

        JsonResult result = projectService.getProById(id);
        Project project = (Project) result.getData();
        List<Integer> empList = employeeService.getEmpIds(id);

        List<Attendance> attList = new ArrayList<>(empList.size());
        AtomicInteger index = new AtomicInteger();
        empList.forEach(emp -> {
            Attendance attendance = new Attendance();
            index.getAndIncrement();
            attendance.setProId(project.getId());
            attendance.setWorkDate(localDate);
            attendance.setEmpId(emp);
            attendance.setCreateTime(LocalDate.now());
            attendance.setManager(project.getManagerName1());
            attendance.setSalary(project.getSalary());
            attendance.setOverSalary(project.getOverSalary());
            attList.add(attendance);
        });

        if (attendanceService.saveBatch(attList)) {
            return JsonResult.insertSuccess();
        }
        return JsonResult.insertError();
    }


    @ApiOperation("项目人员整体取消打卡")
    @DeleteMapping("/att/pro")
    public JsonResult delProjectAtt(Integer id,@RequestParam(required = false)String  date){
        return attendanceService.removeProjectAtt(id,date);
    }


}
