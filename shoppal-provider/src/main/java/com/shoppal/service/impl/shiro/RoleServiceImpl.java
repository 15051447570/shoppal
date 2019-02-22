package com.shoppal.service.impl.shiro;

import com.alibaba.dubbo.config.annotation.Service;
import com.shoppal.consumer.shiro.RoleService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl  implements RoleService {

    @Override
    public Set<String> getRolesByUserId(Long uid) {
        Set<String> roles = new HashSet<>();
        //三种编程语言代表三种角色：js程序员、java程序员、c++程序员
        roles.add("js");
        roles.add("java");
        roles.add("cpp");
        return roles;
    }
}
