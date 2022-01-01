package com.xd.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@author xd
*@create 2021/12/25
*@description 
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "结果返回实体类")
public class JsonResult {
    // 返回的
    @ApiModelProperty("响应码")
    private  Integer code;
    // 返回的信息
    @ApiModelProperty("消息")
    private String message;
    // 返回的数据
    @ApiModelProperty("数据")
    private Object data;

    /**
     * 请求成功并且需要返回数据的结果
     * @param message 返回的消息
     * @param data  返回的数据
     * @return JsonResult
     */
    public static JsonResult success(String message, Object data ){
        return new JsonResult(200,message,data);
    }

    /**
     * 请求成功但是不需要返回数据
     * @param message 消息
     * @return JsonResult
     */
    public static JsonResult success(String message){
        return new JsonResult(200,message,null);
    }

    /**
     * 请求失败需要返回信息
     * @param message 信息
     * @return JsonResult
     */
    public static JsonResult error(String message){
        return new JsonResult(500,message,null);
    }


    public static JsonResult insertSuccess(){
        return new JsonResult(200,"插入成功",null);
    }

    public static JsonResult updateSuccess(){
        return new JsonResult(200,"更新成功",null);
    }

    public static JsonResult deleteSuccess(){
        return new JsonResult(200,"删除成功",null);
    }

    public static JsonResult selectSuccess(Object data){
        return new JsonResult(200,"查询成功",data);
    }
    
    public static JsonResult insertError(){
        return new JsonResult(200,"插入失败",null);
    }

    public static JsonResult updateError(){
        return new JsonResult(200,"更新失败",null);
    }

    public static JsonResult deleteError(){
        return new JsonResult(200,"删除失败",null);
    }

    public static JsonResult selectError(){
        return new JsonResult(500,"查询失败",null);
    }








}
