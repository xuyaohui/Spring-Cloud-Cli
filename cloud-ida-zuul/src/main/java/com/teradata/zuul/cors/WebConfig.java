package com.teradata.zuul.cors;

import com.teradata.zuul.interceptor.SsoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许全部请求跨域
        registry.addMapping("/**")
                //.allowedOrigins("http://localhost:8080")
//                .allowedOrigins("http://localhost:8088")
                .allowedMethods("GET", "POST","OPTIONS")
                .allowCredentials(true).maxAge(3600);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SsoInterceptor())
                .excludePathPatterns("/shiro/updatePermission")
                .excludePathPatterns("/login/auth")
                .addPathPatterns("/**");

        //验证有问题，待解决
//        registry.addInterceptor(new LoginInterceptor())
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/login/**")
//                .addPathPatterns("/**");
    }
}
