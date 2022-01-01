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
@TableName("d_project")
public class Project implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private Integer salary;

    @TableField("over_salary")
    private Integer overSalary;

    @TableField("salary_note")
    private String salaryNote;

    private String company;

    private String address;


    @TableField(value = "m_id1")
    private Integer mid1;

    @TableField(value = "m_id2")
    private Integer mid2;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate createTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate updateTime;

    private Integer isenable;


    @TableField(value = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate endTime;

    @TableField(exist = false)
    private String managerName1;

    @TableField(exist = false)
    private String managerName2;

}
