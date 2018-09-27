package com.teradata.tag.controller;

import com.teradata.common.bean.ResponseBean;
import com.teradata.common.util.JWTUtil;
import com.teradata.tag.bean.User;
import com.teradata.tag.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/9/6
 */

@RestController
@RequestMapping("/tag")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/401")
    public String error401(){
        return "未登录";
    }

    @GetMapping("/getUserById")
    public ResponseBean getUserById(ServletRequest req, ServletResponse resp){

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String authorization = httpServletRequest.getHeader("Authorization");
        System.out.println("获取的auth: "+authorization);
        if (authorization!=null){
            String userId= JWTUtil.getUsername(authorization);
            List<User> users=userRepository.getUserByUserId(userId);
            return new ResponseBean(200,"success",users);
        }

        return new ResponseBean(400,"false","查无此人");
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
