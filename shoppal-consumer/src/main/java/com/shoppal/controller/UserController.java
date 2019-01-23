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
package com.shoppal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shoppal.consumer.user.UserService;
import com.shoppal.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo controller
 */
@RestController
@CrossOrigin//允许跨越访问
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private UserService userService;

    @RequestMapping("/searchUser")
    public String sayHello(@RequestParam int id) {
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
        User user = userService.selectUser(id);
        System.out.println(user == null ? "" : user.getUserName());
        return user == null ? "" : user.getUserName();
    }

}
