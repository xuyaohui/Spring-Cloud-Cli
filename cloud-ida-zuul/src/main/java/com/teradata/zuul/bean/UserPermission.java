package com.teradata.zuul.bean;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/8/29
 */
public class UserPermission {

    private Integer userId;
    private String permission;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
