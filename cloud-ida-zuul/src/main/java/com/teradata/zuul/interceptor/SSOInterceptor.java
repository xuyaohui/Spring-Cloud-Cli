package com.teradata.zuul.interceptor;

import com.google.gson.Gson;
import com.teradata.common.bean.ErrorEnum;
import com.teradata.common.util.JWTUtil;
import com.teradata.zuul.service.RedisService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @auther xuyaohui
 * @date 2018/9/20
 *
 * 单点登录拦截器
 */
public class SsoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {

        String authorization = servletRequest.getHeader("Authorization");

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(servletRequest.getServletContext());
        RedisService redisService = (RedisService) factory.getBean("redisService");
        boolean flag= false;

        if (authorization != null && JWTUtil.verifyToken(authorization)){
            //校验登陆的token是否与缓存中的token保持一致
            String userId = JWTUtil.getUsername(authorization);
            if (((String)redisService.get(userId)).equalsIgnoreCase(authorization)){
                flag = true;
                return flag;
            }
        }

        servletResponse.setCharacterEncoding("UTF-8");
        //Subject subject = getSubject(request, response);
        PrintWriter printWriter = servletResponse.getWriter();
        servletResponse.setContentType("application/json;charset=UTF-8");
        servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        servletResponse.setHeader("Vary", "Origin");
        String respStr;

        respStr = "you have not right to access";


        printWriter.write(new Gson().toJson(new com.teradata.common.bean.ResponseBean(ErrorEnum.ERROR_10002.getErrorCode(),ErrorEnum.ERROR_10002.getErrorMsg(),"")));
        printWriter.flush();
        servletResponse.setHeader("content-Length", respStr.getBytes().length + "");
        return flag;
    }


}
