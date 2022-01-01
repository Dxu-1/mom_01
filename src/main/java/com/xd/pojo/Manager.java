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
@TableName("d_manager")
public class Manager implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer sex;

    private String company;


    @TableField("evl_score")
    private Integer evlScore;

    @TableField("evl_content")
    private String evlContent;

    private String phone;

    private String wechat;

    private String hometown;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private LocalDate createTime;

    private Integer isdelete;

}
