package com.shoppal.consumer.shiro;

import com.shoppal.model.ShiroUser;

public interface ShiroUserService {

     ShiroUser findUserByName(String uname);

}
