package com.shoppal.consumer.shiro;

import java.util.Set;

public interface RoleService {
     Set<String> getRolesByUserId(Long uid);

}
