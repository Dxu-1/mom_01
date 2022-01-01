package com.xd.pojo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
*@author xd
*@create 2022/1/1
*@description 
*/
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProjectMonthAtt implements Serializable {

    private String month;
    private String name;
    private Integer attCount;

}
