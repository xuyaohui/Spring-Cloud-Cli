package com.teradata.tag.aop;

import com.teradata.common.util.JWTUtil;
import com.teradata.tag.annotation.MyRequireRole;
import com.teradata.tag.feign.LoginFeign;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xuyaohui
 * @date 2018/8/6 0006 下午 3:11
 */
@Component
@Aspect
public class MyRequireRoleAop {

    private static final Logger logger
            = LoggerFactory.getLogger(MyRequireRoleAop.class);

    @Autowired
    private LoginFeign loginFeign;


    @Pointcut("@annotation(com.teradata.tag.annotation.MyRequireRole)")
    public void checkRole(){

    }

    @Before(value = "checkRole()&&@annotation(myRequireRole)")
    public void doBeforeRun(MyRequireRole myRequireRole){
        logger.info("aop 拦截");
    }

    @Around(value = "checkRole()&&@annotation(myRequireRole)")
    public Object  run(ProceedingJoinPoint pjp, MyRequireRole myRequireRole){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization != ""){
            boolean flag = JWTUtil.verifyToken(authorization.trim());
            if (flag){
                try {
                    Object o = pjp.proceed();
                    return  o;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }else{
                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                try {
                    response.sendRedirect("/401");
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;

                }
            }
        }else{
            logger.info("认证未通过...");
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            try {
                response.sendRedirect("/401");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        return null;
    }

    @After(value = "checkRole()&&@annotation(myRequireRole)")
    public void doAfter( MyRequireRole myRequireRole){
        //日志输出
        logger.info("aop 拦截结束...");
    }

}
