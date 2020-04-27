package com.xieyy.boot.api;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.xieyy.boot.dto.ResultDTO;
import com.xieyy.boot.enums.XieyyExceptionEnum;
import com.xieyy.boot.exception.XieyyException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class RedisController {

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    BloomFilter<String> bloomFilter = null;

    @PostMapping(value = "/string/query")
    public ResultDTO queryString(String key) {
        return ResultDTO.success(stringRedisTemplate.opsForValue().get(key));
    }

    @PostMapping(value = "/string/set")
    public ResultDTO setString(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XieyyException(XieyyExceptionEnum.PARAMS_ERROR);
        }
        return ResultDTO.success();
    }

    @PostMapping(value = "/list/queryAll")
    public ResultDTO queryList(String key) {
        return ResultDTO.success(stringRedisTemplate.opsForList().range(key, 0, -1));
    }

    @PostMapping(value = "/list/query")
    public ResultDTO queryList(String key, int start, int end) {
        return ResultDTO.success(stringRedisTemplate.opsForList().range(key, start, end));
    }

    /**
     * 使用redis实现分布式锁
     *
     * @param productId
     * @return
     */
    @PostMapping(value = "/product/buy")
    public ResultDTO buy(String productId) {
        RLock lock = redisson.getLock(productId);
        try {
            lock.lock(5, TimeUnit.SECONDS);
            //处理业务逻辑
            System.out.println("已经加锁了。。。");
        } finally {
            lock.unlock();
        }
        return ResultDTO.success();
    }

    /**
     * 使用guava的bloomFilter
     *
     * @param productId
     * @return
     */
    @PostMapping(value = "/bloom")
    public ResultDTO bloom(String productId) {
        boolean mightContain = bloomFilter.mightContain(productId);
        log.info("可能存在：{}", mightContain);
        return ResultDTO.success();
    }

    private List<String> getAllKey() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i + "");
        }
        return list;
    }

    @PostConstruct
    public void init() {
        log.info("开始加载。。。。");
        List<String> list = getAllKey();
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), list.size());
        for (int i = 0; i < list.size(); i++) {
            bloomFilter.put(list.get(i));
        }
        log.info("加载完毕。。。。");
    }


}
