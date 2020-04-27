package com.xieyy.boot.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LimitController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/limit/{lockkey}")
    public String limit(@PathVariable("lockkey") String lockKey) {
        String lockKeyValue = redisTemplate.opsForList().rightPop(lockKey);
        if (StringUtils.isBlank(lockKeyValue)) {
            log.info("被限流中。。。。");
        }
        return "success";
    }
}
