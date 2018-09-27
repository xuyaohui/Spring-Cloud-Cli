package com.teradata.zuul.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * @author xuyaohui
 * @date 2018-8-29
 *
 */


public class User implements Serializable{

    //用户标识
    private String userAccount;

    private String password;

    //用户角色（多角色）
    private String roles;

    private Set<String> permission;

    /**
     * 该用户所有菜单
     */
    private Set<String> menuList;

    public Set<String> getPermission() {
        return permission;
    }

    public void setPermission(Set<String> permission) {
        this.permission = permission;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<String> getMenuList() {
        return menuList;
    }

    public void setMenuList(Set<String> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                ", role='" + roles + '\'' +
                '}';
    }
}
