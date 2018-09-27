package com.teradata.zuul.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teradata.common.util.JWTUtil;
import com.teradata.zuul.bean.ResponseBean;
import com.teradata.zuul.bean.User;
import com.teradata.zuul.feign.TestFeign;
import com.teradata.zuul.service.ShiroService;
import com.teradata.zuul.service.UserService;
import com.teradata.zuul.service.RedisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * 注意点：由于与springcloud融合，需加入peoduces，否则返回xml格式数据(方式1)
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    // 过期时间5分钟
    private static final long EXPIRE_TIME = 120*60*1000;

    //设置私钥
    private static final String SECRET = "xuyaohui";

    @Autowired
    private TestFeign testFeign;

    @Autowired
    private UserService userService;

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    RedisService redisService;

    //动态更新权限
    @Autowired
    private ShiroService shiroService;

    @GetMapping("/testFeign")
    public String testFeign(){
        System.out.println("testFeign success!成功");
        return testFeign.getLoginToken();
    }

    @GetMapping("/shiro/updatePermission")
    public ResponseBean updateShiroPermission(){
        System.out.println("更新权限");
        shiroService.updatePermission(shiroFilterFactoryBean);
        return new ResponseBean(200,"success","success");
    }

    /**
     *     注意点：由于与springcloud融合，需加入peoduces，否则返回xml式数据（方式2）
     */
    @ApiOperation(value="获取用户详细信息", notes="根据userId获取用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(value = "/getPer",produces = {"application/json;charset=UTF-8"})
    public ResponseBean getUserPermission(String userId){

        ResponseBean responseBean= new ResponseBean(200,"success",userService.getUserByAccount(userId));
        return responseBean;
    }

    /**
     * 需要登录验证
     * @return
     */
    @GetMapping("/test")
    //@RequiresAuthentication
    public String test() throws Exception{
        if (1==1){
            throw  new Exception("异常");
        }
        return "success";
    }

    @GetMapping("/getUser")
    public ResponseBean testGetUser(String userId){

        User user=userService.getUserByAccount(userId);
        return new ResponseBean(200,"success",user);
    }

    @RequestMapping("/token")
    public String test(String userId){
        try {
            User user=userService.getUserByAccount(userId);
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            String token=JWT.create()
                    .withClaim("username", userId)
                    .withClaim("roles",user.getRoles())
                    .withExpiresAt(date)
                    .sign(algorithm);
            System.out.println("before: "+token);
            redisService.set(userId,token);
            System.out.println("after: "+ redisService.get(userId));
            return token;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        //return JWTUtil.sign("idaadmin", "dn1rjz8Hzuo=");
    }


    /**
     * vue前端登录实现
     * @param jsonObject 包含用户名和密码
     * @return jwt token
     */
    @PostMapping("/login/auth")
    public ResponseBean login(@RequestBody JSONObject jsonObject){
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        try {
            User user=userService.getUserByAccount(username);

            if (user.getPassword().equalsIgnoreCase(password)){
                Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                String token= JWT.create()
                        .withClaim("username", username)
                        .withClaim("roles",user.getRoles())
                        .withExpiresAt(date)
                        .sign(algorithm);
                redisService.remove(username);
                System.out.println("before: "+token);
                redisService.set(username,token);
                System.out.println("after: "+ redisService.get(username));

                // 附带username信息
                return new ResponseBean(200,"success",token);
            }
            return new ResponseBean(200,"false","用户名或密码错误");

        } catch (UnsupportedEncodingException e) {
            return new ResponseBean(200,"false","异常抛出");
        }
    }

    /**
     * 获取用户权限信息
     * @return
     */
    @GetMapping("/login/getInfo")
    public ResponseBean getInfo(ServletRequest req, ServletResponse resp) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String authorization = httpServletRequest.getHeader("Authorization");
        System.out.println("获取的auth: "+authorization);
        if (authorization!=null){
            String userId=JWTUtil.getUsername(authorization);
            return new ResponseBean(200,"success",userService.getUserByAccount(userId));
        }
        return new ResponseBean(401,"false","获取用户信息出错");
    }

}
