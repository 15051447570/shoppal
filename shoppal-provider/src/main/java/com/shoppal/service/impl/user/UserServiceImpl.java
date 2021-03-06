/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shoppal.service.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.shoppal.consumer.user.UserService;
import com.shoppal.mapper.user.UserMapper;
import com.shoppal.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(int userId) {
        logger.trace("日志输出 trace" + userId);
        logger.debug("日志输出 debug" + userId);
        logger.info("日志输出 info" + userId);
        logger.warn("日志输出 warn" + userId);
        logger.error("日志输出 error" + userId);
        return userMapper.selectByPrimaryKey(userId);
    }
}
