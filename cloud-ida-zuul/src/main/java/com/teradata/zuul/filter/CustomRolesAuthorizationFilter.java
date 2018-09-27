package com.teradata.zuul.filter;

import com.google.gson.Gson;
import com.teradata.common.bean.ErrorEnum;
import com.teradata.common.bean.JWTToken;
import com.teradata.common.util.JWTUtil;
import com.teradata.zuul.bean.ResponseBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {
    @Override
    public boolean isAccessAllowed(ServletRequest req, ServletResponse resp, Object mappedValue) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String authorization = httpServletRequest.getHeader("Authorization");
        if(authorization == null){
            return false;
        }

        String roles=JWTUtil.getRolesByToken(authorization);
        //Subject subject = getSubject(req, resp);
        String[] rolesArray = (String[]) mappedValue;
        //如果没有角色限制，直接放行
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String aRolesArray : rolesArray) {
            //若当前用户是rolesArray中的任何一个，则有权限访问
            if (roles.contains(aRolesArray)) {
                return true;
            }
        }

        return false;
    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        HttpServletRequest servletRequest = (HttpServletRequest) request;
//        HttpServletResponse servletResponse = (HttpServletResponse) response;
//        servletResponse.setCharacterEncoding("UTF-8");
//        //Subject subject = getSubject(request, response);
//        PrintWriter printWriter = servletResponse.getWriter();
//        servletResponse.setContentType("application/json;charset=UTF-8");
//        servletResponse.setHeader("Access-Control-Allow-Origin", servletRequest.getHeader("Origin"));
//        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        servletResponse.setHeader("Vary", "Origin");
//        String respStr;
//
//        respStr = "you have not right to access";
//
//
//        printWriter.write(new Gson().toJson(new com.teradata.common.bean.ResponseBean(ErrorEnum.ERROR_401.getErrorCode(),ErrorEnum.ERROR_401.getErrorMsg(),"")));
//        printWriter.flush();
//        servletResponse.setHeader("content-Length", respStr.getBytes().length + "");
//
////        PrintWriter out = null;
////        HttpServletResponse res = (HttpServletResponse) response;
////        try {
////            res.setCharacterEncoding("UTF-8");
////            res.setContentType("application/json");
////            out = response.getWriter();
////            out.println("无权限访问");
////            //out.println(new Gson().toJson(new com.teradata.common.bean.ResponseBean(ErrorEnum.ERROR_401.getErrorCode(),ErrorEnum.ERROR_401.getErrorMsg(),"")));
////        } catch (Exception e) {
////        } finally {
////            if (null != out) {
////                out.flush();
////                out.close();
////            }
////        }
//        return false;
//    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        //处理跨域问题，跨域的请求首先会发一个options类型的请求
        if (servletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }
        boolean isAccess = isAccessAllowed(request, response, mappedValue);

        if (isAccess){
            return true;
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


        printWriter.write(new Gson().toJson(new com.teradata.common.bean.ResponseBean(ErrorEnum.ERROR_401.getErrorCode(),ErrorEnum.ERROR_401.getErrorMsg(),"")));
        printWriter.flush();
        servletResponse.setHeader("content-Length", respStr.getBytes().length + "");
        return false;
    }
}
