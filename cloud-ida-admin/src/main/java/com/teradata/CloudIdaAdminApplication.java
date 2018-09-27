package com.teradata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
//@EnableTransactionManagement//开启事务支持
@MapperScan(value="com.teradata.dao")
@EnableSwagger2
@EnableFeignClients
public class CloudIdaAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudIdaAdminApplication.class, args);
	}
}
