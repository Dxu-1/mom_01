package com.xd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
*@author xd
*@create 2021/12/26
*@description 
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("d_attendance")
public class Attendance implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("emp_id")
    private Integer empId;

    @TableField("pro_id")
    private Integer proId;

    private Integer salary;

    @TableField("over_salary")
    private Integer overSalary;

    @TableField("salary_note")
    private String salaryNote;


    @TableField("work_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate workDate;

    @TableField("full_day")
    private Integer fullDay;

    private Double overtime;

    private String note;

    private String manager;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate updateTime;
}
