package com.teradata.feign;

import org.springframework.stereotype.Component;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/9/6
 */
@Component
public class ShiroUpdatePermissionError  implements ShiroFeign{

    @Override
    public String updatePermission() {
        return "update error";
    }
}
