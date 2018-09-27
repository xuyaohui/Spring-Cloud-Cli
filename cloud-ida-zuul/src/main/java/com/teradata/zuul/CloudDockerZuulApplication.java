package com.teradata.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * zuul不仅可以作为路由转发，还可以作为权限验证
 *
 * @author xuyaohui
 * @date 2018-8-6
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
public class CloudDockerZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDockerZuulApplication.class, args);
    }
}
