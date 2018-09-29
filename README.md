SpringCloud 脚手架
===

简易教程
---
https://blog.csdn.net/u014271612/article/details/82866913

项目架构图
---
![image](https://github.com/xuyaohui/cloud-ida-cli/blob/master/images/%E6%9E%B6%E6%9E%84%E5%9B%BE.png)

所使用相关技术：
---

注册中心：Spring Cloud Eureka

网关中心：Spring Cloud Zuul

服务配置：Spring Cloud Config

链路追踪：Spring Cloud Zipkin

数据库连接：Druid、Mybatis

服务鉴权：Shiro+JWT

数据库：PostgreSQL

日志收集：ELK(服务安装在虚拟机)

服务之间调用： Feign

熔断机制：Hystrix

消息队列：RabbitMQ

系统各模块介绍
---

cloud-ida：项目父模块，所有以下子模块依赖该父模块（可在该pom文件加入所需要的依赖）

cloud-ida-admin : 后台管理模块（包含前后端），包括用户、角色、权限管理及服务监控

cloud-ida-admin-server : 使用springboot admin，监控各服务运行状况

cloud-ida-common : common模块，封装模块常用bean及工具类

cloud-ida-config : 分布式配置中心，可将各模块所需的配置放到该中心（dev/uat/pro）

cloud-ida-eureka : 服务发现、注册中心

cloud-ida-service : 业务服务模块（可按业务拆分成多个服务）

cloud-ida-zipkin : 服务链路追踪

cloud-ida-zuul : 微服务网关层，所有请求都经过网关请求，此模块中也有shiro认证、鉴权

未完待续...

占用的端口
---

cloud-ida-admin： 9000 

cloud-ida-admin-server： 9010

cloud-ida-config : 8888

cloud-ida-eureka : 8761

cloud-ida-service-tag : 8087

cloud-ida-zipkin : 9411

cloud-ida-zuul : 9084

如何运行该系统？
---

*   保证各服务模块使用的端口未被占用
*   根据表模型创建库（如cloud-ida）、表，并插入相应的测试数据
*   配置各模块设置数据库的url、用户名和密码，可通过全局搜索"jdbc:postgresql",替换成自己的配置
*   启动顺序：cloud-ida-eureka->cloud-ida-config->cloud-ida-admin server->cloud-ida-admin->cloud-ida-service->cloud-ida-zuul->cloud-ida-zipkin
*   各模块使用springboot创建，找到个服务对用的application,运行main函数即可，如cloud-ida-zuul的application为CloudDockerZuulApplication
*   启动vue前端，切换到vue-front目录下，运行cnpm run dev(事先安装node.js、cnpm、vue)


系统体验
---

*   后台管理系统：http://localhost:9000/index 用户名/密码：admin/123456
*   前端系统： http://localhost:9521 用户名/密码：admin/123456

系统截图
---

后台管理系统截图
![image](https://github.com/xuyaohui/cloud-ida-cli/blob/master/images/admin.PNG)

后端赋权

![image](https://github.com/xuyaohui/cloud-ida-cli/blob/master/images/auth.PNG)

服务监控

![image](https://github.com/xuyaohui/cloud-ida-cli/blob/master/images/watch.PNG)

vue登录界面

![image](https://github.com/xuyaohui/cloud-ida-cli/blob/master/images/login.PNG)

vue前端测试界面

![image](https://github.com/xuyaohui/cloud-ida-cli/blob/master/images/vue.PNG)







