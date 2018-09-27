package com.teradata.dao;

import com.teradata.bean.po.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    Integer batchInsert(List<UserRole> userRoles);

    Integer deleteByUserId(Integer userId);

    List<UserRole> listUserRoles(UserRole userRole);
}