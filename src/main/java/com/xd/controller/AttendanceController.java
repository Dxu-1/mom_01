package com.xd.controller;

import com.xd.pojo.Attendance;
import com.xd.pojo.JsonResult;
import com.xd.service.IAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        LocalDate localDate;
        if (date == null){
            localDate = LocalDate.now();
        }else{
            try {
                localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }catch (DateTimeParseException e){
                return JsonResult.error("时间格式不对");
            }
        }
        return attendanceService.getProjectAttByDate(proId,localDate);
    }

    @ApiOperation("根据项目月份查询")
    @GetMapping("/att/month")
    public JsonResult getAttByProjectMonth(@RequestParam(required = false)String  date ,@RequestParam Integer proId){
        LocalDate localDate;
        if (date == null){
            localDate = LocalDate.now();
        }else{
            try {
                localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }catch (DateTimeParseException e){
                return JsonResult.error("时间格式不对");
            }
        }
        return attendanceService.getProjectAttByMonth(proId,localDate);
    }



}
