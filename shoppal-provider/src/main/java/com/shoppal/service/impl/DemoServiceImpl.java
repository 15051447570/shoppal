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
package com.shoppal.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shoppal.consumer.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

/**
 * DefaultDemoService
 */
@Service
public class DemoServiceImpl implements DemoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    public String sayHello(@QueryParam("name") String name) {
        logger.trace("日志输出 trace" + name);
        logger.debug("日志输出 debug" + name);
        logger.info("日志输出 info" + name);
        logger.warn("日志输出 warn" + name);
        logger.error("日志输出 error" + name);
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        System.out.println(operations.get(name));
        operations.set(name, name + Math.random());
        System.out.println(operations.get(name));
        return "Hello, " + name + " (from Spring Boot)";
    }
}