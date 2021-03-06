package com.xd.service.impl;

import com.xd.mapper.AttendanceMapper;
import com.xd.pojo.ProjectMonthSalary;
import com.xd.service.ISalaryService;
import com.xd.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
*@author xd
*@create 2022/1/1
*@description 
*/
@Service
public class SalaryServiceImpl implements ISalaryService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public JsonResult getProjectSalaryByMonth(Integer id, LocalDate localDate) {

        List<ProjectMonthSalary> projectMonthSalaryList = attendanceMapper.selectSalaryByMonth(localDate, id);

        for (ProjectMonthSalary projectMonthSalary : projectMonthSalaryList) {
            System.out.println(projectMonthSalary);
        }

        return JsonResult.selectSuccess(projectMonthSalaryList);
    }
}
