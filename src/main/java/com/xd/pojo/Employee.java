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
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("d_employee")
public class Employee implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sex;

    @TableField("recom_emp_id")
    private Integer recomEmpId;

    @TableField(value = "nick_name")
    private String nickName;

    private Integer skill;

    private Integer iswork;

    @TableField("pro_id")
    private Integer proId;

    @TableField("id_card")
    private String idCard;

    @TableField("evl_score")
    private Integer evlScore;

    @TableField("evl_content")
    private String evlContent;

    private String phone;

    private String wechat;

    private String hometown;

    @TableField("bank_num")
    private String bankNum;

    @TableField("other_bank")
    private String otherBank;

    private String address;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate updateTime;


    private Integer isdelete;

}
