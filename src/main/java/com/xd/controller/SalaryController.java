package com.xd.controller;

import com.xd.service.IAttendanceService;
import com.xd.service.ISalaryService;
import com.xd.utils.DateUtil;
import com.xd.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
*@author xd
*@create 2022/1/1
*@description 
*/
@RestController
@RequestMapping("/api")
@Api(tags = "工资管理")
public class SalaryController {

    @Autowired
    private IAttendanceService attendanceService;

    @Autowired
    private ISalaryService salaryService;

    @GetMapping("/sal")
    @ApiOperation("获取项目下所有人某月工资")
    public JsonResult getProjectSalary(Integer id,@RequestParam(required = false) String date){
        if (DateUtil.stringDate(date) instanceof JsonResult){
            return (JsonResult)DateUtil.stringDate(date);
        }
        LocalDate localDate = (LocalDate) DateUtil.stringDate(date);


        return  salaryService.getProjectSalaryByMonth(id, localDate);
    }




}
