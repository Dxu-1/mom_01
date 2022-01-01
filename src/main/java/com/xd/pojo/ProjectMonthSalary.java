package com.xd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@author xd
*@create 2022/1/1
*@description 
*/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMonthSalary extends ProjectMonthAtt implements Serializable{

    private Integer overSalary;
    private Integer sumOverSalary;
    private Integer  overtime;
    private Integer fullDay;
    private String  salaryNote;
    private Integer salary;
    private Integer sumSalary;
}
