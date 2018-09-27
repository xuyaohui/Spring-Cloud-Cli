package com.teradata.service.interfaces;

import com.teradata.bean.po.Role;
import com.teradata.bean.po.RolePermission;
import com.teradata.bean.vo.RolePermissionVO;

import java.util.List;

public interface RoleService {

    Integer add(Role role);

    Integer delete(Integer roleId);

    Integer update(Role role);

    List<Role> listRole(Role role);

    Role selectByPrimaryKey(Integer roleId);

    List<RolePermission> listRolepermission(Integer roleId);

    List<RolePermissionVO> listRolepermissions(Integer roleId);

    Integer batchInsert(List<RolePermission> rolepermissions);

    Integer deleteByRoleId(Integer roleId);

}
