package com.teradata.eureka.init;

import com.teradata.eureka.CloudDockerEurekaApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/9/11
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CloudDockerEurekaApplication.class);
    }
}
