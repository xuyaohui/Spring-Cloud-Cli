package com.teradata.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xuyaohui
 * @date 2018-8-29
 */

@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class, MybatisAutoConfiguration.class})
public class CloudIdaAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudIdaAdminServerApplication.class, args);
	}
}
