package com.teradata.tag.feign;


import org.springframework.stereotype.Component;

/**
 * Created by xuyaohui on 2018/8/15.
 */
@Component
public class LoginFeignError implements LoginFeign {

    @Override
    public String isLogin() {
        return "login 获取失败";
    }

    @Override
    public String getToken() {
        return "获取Token错误";
    }
}
