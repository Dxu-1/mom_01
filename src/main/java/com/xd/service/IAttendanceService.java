package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.mapper.AttendanceMapper;
import com.xd.pojo.Attendance;
import com.xd.pojo.JsonResult;

import java.time.LocalDate;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
public interface IAttendanceService extends IService<Attendance> {

    JsonResult addAtt(Attendance attendance);

    JsonResult getProjectAttByDate(Integer proId, LocalDate date);

    JsonResult updateAtt(Attendance attendance);
}
