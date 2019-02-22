package com.shoppal.service.impl.shiro;

import com.alibaba.dubbo.config.annotation.Service;
import com.shoppal.consumer.shiro.ShiroUserService;
import com.shoppal.model.ShiroUser;

import java.util.Date;
import java.util.Random;

@Service
public class ShiroUserServiceImpl implements ShiroUserService {
    @Override
    public ShiroUser findUserByName(String uname) {
        ShiroUser user = new ShiroUser();
        user.setUname(uname);
        user.setNick(uname+"NICK");
        user.setPwd("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");//密码明文是123456
        user.setSalt("wxKYXuTPST5SG0jMQzVPsg==");//加密密码的盐值
        user.setUid(new Random().nextLong());//随机分配一个id
        user.setCreated(new Date());
        return user;
    }
}
