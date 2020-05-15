package com.xieyy.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {

    @Async
    public void asyncTest(String request) {
        log.info("AsyncService:{}", request);
    }
}