package com.teradata.zuul.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xuyaohui
 * @date 2018/8/6 0006 上午 11:06
 */

@FeignClient("cloud-shiro")
public interface TestFeign {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    String getLoginToken();

}
