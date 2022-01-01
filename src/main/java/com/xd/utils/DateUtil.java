package com.xd.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
*@author xd
*@create 2022/1/1
*@description 
*/
public class DateUtil {

    public static Object stringDate(String date){
        LocalDate localDate;
        if (date == null){
            localDate = LocalDate.now();
        }else{
            try {
                localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }catch (DateTimeParseException e){
                return JsonResult.error("时间格式不对");
            }
        }
        return localDate;
    }

}
