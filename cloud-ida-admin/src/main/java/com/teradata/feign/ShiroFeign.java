package com.teradata.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/9/6
 */
@FeignClient(name = "cloud-zuul",fallback = ShiroUpdatePermissionError.class)
public interface ShiroFeign {

    @RequestMapping(value = "/shiro/updatePermission",method = RequestMethod.GET)
    String updatePermission();

}
