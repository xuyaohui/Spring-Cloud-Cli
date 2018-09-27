package com.teradata.service.impl;

import com.teradata.bean.po.Permission;
import com.teradata.dao.PermissionMapper;
import com.teradata.service.interfaces.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Integer add(Permission permission) {
        return permissionMapper.insertSelective(permission);
    }

    @Override
    public Integer delete(Integer permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public Integer update(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public Permission selectByPrimaryKey(Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }

    @Override
    public List<Permission> listPermissionWithParName(Permission permission) {
        return permissionMapper.listPermissionWithParName(permission);
    }

    @Override
    public List<Permission> listPermission(Permission permission) {
        return permissionMapper.listPermission(permission);
    }

    @Override
    public List<Permission> listPermissionView() {
        return permissionMapper.listPermissionView();
    }

    @Override
    public List<Permission> getUserpermissions(Integer userId) {
        return permissionMapper.getUserpermissions(userId);
    }

}
