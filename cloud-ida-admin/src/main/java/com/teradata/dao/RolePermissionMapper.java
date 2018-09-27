package com.teradata.dao;

import com.teradata.bean.po.RolePermission;
import com.teradata.bean.vo.RolePermissionVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RolePermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByRoleId(Integer roleId);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    List<RolePermissionVO> listRolepermissions(Integer roleId);

    List<RolePermission> listRolepermission(Integer roleId);

    Integer batchInsert(List<RolePermission> rolepermissions);
}