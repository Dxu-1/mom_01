package com.xd.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@author xd
*@create 2021/12/28
*@description 
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestInfo implements Serializable {

    private Integer sex;
    private String nickName;
    private Integer skill;
    private Integer iswork;
    private Integer isdelete;
    private Integer currentPage;
    private Integer pageSize;
    private Integer order;
}
