package com.xieyy.boot.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的拒绝策略，只需要将信息打印出来或者可以存入数据库等操作
 */
@Slf4j
public class TestRejected implements RejectedExecutionHandler {

    public TestRejected() {
    }

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        log.info("被线程池拒绝的任务>>>runnable:{},executor:{}", runnable, executor);
    }
}


