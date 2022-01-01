package com.xd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.mapper.AttendanceMapper;
import com.xd.mapper.EmployeeMapper;
import com.xd.mapper.ProjectMapper;
import com.xd.pojo.*;
import com.xd.service.IAttendanceService;
import com.xd.utils.DateUtil;
import com.xd.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public JsonResult addAtt(Attendance attendance) {

        attendance.setId(null);
        Employee employee = employeeMapper.selectById(attendance.getEmpId());

        if (!employee.getProId().equals(attendance.getProId())){
            return JsonResult.error("该员工不在项目下");
        }
        Project project =null;
        if (null == attendance.getSalary()){
            project =  projectMapper.getProById(attendance.getProId());
            attendance.setSalary(project.getSalary());
            attendance.setOverSalary(project.getOverSalary());
        }

        if (null == attendance.getManager()){
            if (null == project){
                project =  projectMapper.getProById(attendance.getProId());
            }
            attendance.setManager(project.getManagerName1());
        }
        if (null == attendance.getCreateTime()){
            if (attendance.getWorkDate() == null){
                attendance.setCreateTime(LocalDate.now());
                attendance.setWorkDate(LocalDate.now());
            }else{
                attendance.setCreateTime(attendance.getWorkDate());
            }
        }
        Attendance attendance1 = attendanceMapper.selectOne(new QueryWrapper<Attendance>().eq("emp_id", attendance.getEmpId()).eq("work_date", attendance.getWorkDate()));

        if (attendance1 != null){
            return JsonResult.error("当天已有考勤记录");
        }

        if ( 1 == attendanceMapper.insert(attendance)){
            return JsonResult.insertSuccess();
        }
        return JsonResult.insertError();
    }


    @Override
    public JsonResult getProjectAttByDate(Integer proId, LocalDate date) {
        List<Attendance> attendances = attendanceMapper.selectByDate(proId,date);

        if (attendances.size() == 0){
            return JsonResult.success(date+"没有考勤记录");
        }
        return JsonResult.selectSuccess(attendances);
    }

    @Override
    public JsonResult updateAtt(Attendance attendance) {
        attendance.setUpdateTime(LocalDate.now());

        Attendance attendance1 = attendanceMapper.selectOne(new QueryWrapper<Attendance>().eq("emp_id", attendance.getEmpId()).eq("work_date", attendance.getWorkDate()));
        attendance.setEmpId(null);
        attendance.setProId(null);

        if (null != attendance1){
            if (!attendance.getId().equals(attendance1.getId())){
                return JsonResult.error("当前时间已有记录");
            }
        }

        if (1 ==  attendanceMapper.updateById(attendance)){
            return JsonResult.updateSuccess();
        }
        return JsonResult.updateError();
    }

    @Override
    public JsonResult getProjectAttByMonth(Integer proId, LocalDate localDate) {

        List<ProjectMonthAtt> list = attendanceMapper.selectByMonth(proId, localDate);

        return JsonResult.selectSuccess(list);
    }

    @Override
    public JsonResult removeProjectAtt(Integer id, String date) {
        if (DateUtil.stringDate(date) instanceof JsonResult){
            return (JsonResult)DateUtil.stringDate(date);
        }
        LocalDate localDate = (LocalDate) DateUtil.stringDate(date);

        List<Attendance> list = attendanceMapper.selectList(new QueryWrapper<Attendance>().eq("pro_id", id).eq("work_date", localDate));
        int rows = attendanceMapper.deleteBatchIds(list.stream().map(Attendance::getId).collect(Collectors.toList()));

        if (rows != list.size()){
            return JsonResult.deleteError();
        }

        return JsonResult.deleteSuccess();
    }
}
