package com.xd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.pojo.Attendance;
import com.xd.utils.JsonResult;

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

    JsonResult getProjectAttByMonth(Integer proId, LocalDate localDate);

    JsonResult removeProjectAtt(Integer id, String date);
}
