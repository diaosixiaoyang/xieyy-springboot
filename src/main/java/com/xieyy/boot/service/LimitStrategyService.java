package com.xieyy.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LimitStrategyService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${limit.key}")
    private String lockKey;

    @Value("${limit.val}")
    private int limitThreshold;


    /**
     * 生成限流的数据，往redis中放数据
     */
    public void makeLimitValue() {
        List<String> lockKeyList = new ArrayList<>();
        for (int i = 0; i < limitThreshold; i++) {
            lockKeyList.add(UUID.randomUUID().toString());
        }
        //需要先删除之前的数据
        redisTemplate.delete(lockKey);
        redisTemplate.opsForList().leftPushAll(lockKey, lockKeyList);
    }


    public void executeLua() {
        RedisScript redisScript = new DefaultRedisScript();
        String scriptAsString = redisScript.getScriptAsString();
        Object execute = redisTemplate.execute(redisScript, null, "");

    }

}
