package com.teradata.zuul.service;

import com.teradata.zuul.bean.PermissionRole;
import com.teradata.zuul.repository.UserDao;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 随带写Bug的程序猿
 *
 * @auther xuyaohui
 * @date 2018/8/31
 */

@Service
public class ShiroService {

    @Autowired
    private UserDao userDao;

    //获取所有权限对应的角色，用于shiro动态权限加载
    public Map<String, String> getAllRolesByPermission(){
        Map<String, String> filterRuleMap = new LinkedHashMap<>();

        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/login/**", "anon");
        filterRuleMap.put("/token", "anon");

        filterRuleMap.put("/admin/**", "jwt");
        // 访问401和404页面不通过我们的Filter
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/500", "anon");
        filterRuleMap.put("/getUser", "jwt");

        List<PermissionRole> roles = userDao.getAllRolesByPermission();
        for (PermissionRole role : roles){
            filterRuleMap.put(role.getPermissionUrl(),role.getRoleName());
        }

        return filterRuleMap;
    }

    //更新权限信息
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean
                    .setFilterChainDefinitionMap(getAllRolesByPermission());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean
                    .getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            System.out.println("更新权限成功！！");
        }
    }
}
