package com.teradata.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.teradata.bean.po.Permission;
import com.teradata.service.interfaces.PermissionService;
import com.teradata.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/permission")
    public ResponseEntity listPermissionWithParName(Permission permission, @RequestParam(value="pn",defaultValue = "1")Integer pn, @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize){
        PageHelper.startPage(pn,pageSize);
        List<Permission> list=permissionService.listPermissionWithParName(permission);
        PageInfo<Permission> pageInfo=new PageInfo<>(list,10);
        return  ResponseEntity.success().add("pageInfo",pageInfo);
    }

    @GetMapping("/listPermission")
    public ResponseEntity listPermission(Permission permission){
        List<Permission> list=permissionService.listPermission(permission);
        return  ResponseEntity.success().add("list",list);
    }

    @GetMapping("/permission-view")
    public ResponseEntity listPermissionView(){
        List<Permission> list=permissionService.listPermissionView();
        return  ResponseEntity.success().add("list",list);
    }

    @PostMapping("/permission")
    public ResponseEntity addOrEditPermission(Permission permission){
        if(permission==null){
            return ResponseEntity.error("参数错误");
        }
        if(permission.getpermissionId()==null){//新增
            Integer result=permissionService.add(permission);
            if(result>0){
                return ResponseEntity.success();
            }else{
                return ResponseEntity.error("新增失败");
            }
        }else{
            Integer result=permissionService.update(permission);
            if(result>0){
                return ResponseEntity.success();
            }else{
                return ResponseEntity.error("修改失败");
            }
        }
    }

    @DeleteMapping("/permission/{permissionId}")
    public ResponseEntity deletePermission(@PathVariable("permissionId")Integer permissionId){
        Integer result=permissionService.delete(permissionId);
        if(result>0){
            return ResponseEntity.success();
        }else{
            return ResponseEntity.error("删除失败");
        }
    }

    @GetMapping("/permission/{permissionId}")
    public ResponseEntity getPermission(@PathVariable("permissionId")Integer permissionId){
        Permission permission=permissionService.selectByPrimaryKey(permissionId);
        return ResponseEntity.success().add("permission",permission);
    }
}
