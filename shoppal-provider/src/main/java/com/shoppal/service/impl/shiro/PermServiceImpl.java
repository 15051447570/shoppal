package com.shoppal.service.impl.shiro;

import com.alibaba.dubbo.config.annotation.Service;
import com.shoppal.consumer.shiro.PermService;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermServiceImpl implements PermService {
    @Override
    public Set<String> getPermsByUserId(Long uid) {
        Set<String> perms = new HashSet<>();
        //三种编程语言代表三种角色：js程序员、java程序员、c++程序员
        //js程序员的权限
        perms.add("html:edit");
        //c++程序员的权限
        perms.add("hardware:debug");
        //java程序员的权限
        perms.add("mvn:install");
        perms.add("mvn:clean");
        perms.add("mvn:test");
        return perms;
    }
}
