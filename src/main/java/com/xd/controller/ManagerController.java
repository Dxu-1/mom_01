package com.xd.controller;

import com.xd.utils.JsonResult;
import com.xd.pojo.Manager;
import com.xd.service.IManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
*@author xd
*@create 2021/12/28
*@description 
*/
@RestController
@Api(tags = "经理管理类")
@RequestMapping("/api")
public class ManagerController {

    @Autowired
    private IManagerService managerService;

    @GetMapping("/man")
    @ApiOperation("获取所有经理")
    public JsonResult all(){
        List<Manager> list = managerService.list();
        return JsonResult.selectSuccess(list);
    }

    @DeleteMapping("/man")
    @ApiOperation("删除经理")
    public JsonResult delete(Integer id){

        Manager manager = new Manager();
        manager.setId(id);
        manager.setIsdelete(1);
        if (managerService.updateById(manager)){
            return JsonResult.deleteSuccess();
        }
        return JsonResult.deleteError();
    }

    @PutMapping("/man")
    @ApiOperation("更新经理")
    public JsonResult update(@RequestBody Manager manager){
        manager.setCreateTime(null);

        if (managerService.updateById(manager)){
            return JsonResult.updateSuccess();
        }
        return JsonResult.updateError();
    }


    @PostMapping("/man")
    @ApiOperation("添加经理")
    public JsonResult insert(@RequestBody Manager manager){
        manager.setCreateTime(LocalDate.now());
        manager.setIsdelete(null);
        if (managerService.save(manager)){
            return JsonResult.insertSuccess();
        }
        return JsonResult.insertError();
    }

}
