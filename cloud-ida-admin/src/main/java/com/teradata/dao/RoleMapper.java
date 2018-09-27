package com.teradata.dao;


import com.teradata.bean.po.Role;
import org.springframework.stereotype.Service;

import java.util.List;
public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    List<Role> listRole(Role role);
}