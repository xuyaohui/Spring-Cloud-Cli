package com.teradata.zuul.controller;

import com.alibaba.fastjson.JSONObject;
import com.teradata.common.bean.ErrorEnum;
import com.teradata.common.bean.ResponseBean;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获
 *
 * @author xuyaohui
 * @date 2018-9-18
 */
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    @ExceptionHandler(value = Exception.class)
    public ResponseBean defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
        logger.error("异常", e);
        return new ResponseBean(ErrorEnum.E_400.getErrorCode(),ErrorEnum.E_400.getErrorMsg(),errorObject);
    }

    /**
     * GET/POST请求方法错误的拦截器
     * 因为开发时可能比较常见,而且发生在进入controller之前,上面的拦截器拦截不到这个错误
     * 所以定义了这个拦截器
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseBean httpRequestMethodHandler() throws Exception {
        return new ResponseBean(ErrorEnum.ERROR_500.getErrorCode(),ErrorEnum.ERROR_500.getErrorMsg(),"内部错误");

    }

    /**
     * 权限不足报错拦截
     *
     * @return
     * @throws Exception
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseBean unauthorizedExceptionHandler() throws Exception {
        return new ResponseBean(ErrorEnum.ERROR_401.getErrorCode(),ErrorEnum.ERROR_401.getErrorMsg(),"权限不足");
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseBean unauthenticatedException() throws Exception {
        System.out.println("捕获异常");
        return new ResponseBean(ErrorEnum.ERROR_10001.getErrorCode(),ErrorEnum.ERROR_10001.getErrorMsg(),"用户未登录");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseBean unauthenticationException(){
        return new ResponseBean(ErrorEnum.ERROR_10001.getErrorCode(),ErrorEnum.ERROR_10001.getErrorMsg(),"用户未登录");
    }
}
