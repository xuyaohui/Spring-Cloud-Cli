package com.teradata.dao;

import com.teradata.bean.po.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    List<User> listUser(User record);
}