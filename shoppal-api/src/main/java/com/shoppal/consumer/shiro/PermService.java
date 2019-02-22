package com.shoppal.consumer.shiro;

import java.util.Set;

public interface PermService {
     Set<String> getPermsByUserId(Long uid);
}
