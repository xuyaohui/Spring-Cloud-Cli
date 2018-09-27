package com.teradata.bean.po;

/**
 * @author xuyaohui
 * @date 2018-8-20
 *
 * 权限定义
 *
 * (1)update 新增permissionAuth字段，所需权限
 */
public class Permission {
    private Integer permissionId;

    private String permissionName;

    private String permissionUrl;

    private String permissionAuth;

    private Integer parentpermissionId;

    private String parentpermissionName;

    private Integer permissionLv;

    public String getpermissionAuth() {
        return permissionAuth;
    }

    public void setpermissionAuth(String permissionAuth) {
        this.permissionAuth = permissionAuth;
    }

    public Integer getpermissionId() {
        return permissionId;
    }

    public void setpermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getpermissionName() {
        return permissionName;
    }

    public void setpermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getpermissionUrl() {
        return permissionUrl;
    }

    public void setpermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl == null ? null : permissionUrl.trim();
    }

    public Integer getParentpermissionId() {
        return parentpermissionId;
    }

    public void setParentpermissionId(Integer parentpermissionId) {
        this.parentpermissionId = parentpermissionId;
    }

    public Integer getpermissionLv() {
        return permissionLv;
    }

    public void setpermissionLv(Integer permissionLv) {
        this.permissionLv = permissionLv;
    }

    public String getParentpermissionName() {
        return parentpermissionName;
    }

    public void setParentpermissionName(String parentpermissionName) {
        this.parentpermissionName = parentpermissionName;
    }
}