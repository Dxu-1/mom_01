package com.xd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.pojo.Attendance;
import com.xd.pojo.ProjectMonthAtt;
import com.xd.pojo.ProjectMonthSalary;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author xd
 * @create 2021/12/26
 * @description
 */
public interface AttendanceMapper extends BaseMapper<Attendance> {

    List<Attendance> selectByDate(@Param("proId") Integer proId, @Param("date") LocalDate date);

    List<ProjectMonthAtt> selectByMonth(@Param("proId") Integer proId, @Param("localDate") LocalDate localDate);

    List<ProjectMonthSalary> selectSalaryByMonth(@Param("localDate") LocalDate localDate,@Param("proId") Integer id);
}
