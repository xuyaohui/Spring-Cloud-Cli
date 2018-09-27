package com.teradata.tag.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xuyaohui
 * @date 2018/8/6 0006 下午 4:31
 */

@FeignClient(name = "cloud-shiro",fallback = LoginFeignError.class)
public interface LoginFeign {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    String isLogin();

    /**
     * 从校验中心获取Token
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    String getToken();
}
