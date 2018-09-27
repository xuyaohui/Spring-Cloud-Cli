package com.teradata.zuul.service;

import com.teradata.zuul.bean.PermissionRole;
import com.teradata.zuul.bean.User;
import com.teradata.zuul.bean.UserPermission;
import com.teradata.zuul.repository.UserDao;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/8/29
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserByAccount(String userAccount){

        User user=userDao.getUserByName(userAccount);
        if (user!=null){

           Set<String> permissions = userDao.getUserPermissions(userAccount);

            user.setPermission(permissions);

            //获取菜单
            user.setMenuList(userDao.getMenuListByName(userAccount));
        }

        return user;
    }

}
