package com.xd.service;

import com.xd.utils.JsonResult;

import java.time.LocalDate;

/**
 * @author xd
 * @create 2022/1/1
 * @description
 */
public interface ISalaryService {

    JsonResult getProjectSalaryByMonth(Integer id, LocalDate localDate);
}
