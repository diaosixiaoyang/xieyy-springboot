package com.xieyy.boot.schedule;

import com.xieyy.boot.service.LimitStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LimitSchedule {

    @Autowired
    private LimitStrategyService limitStrategyService;

    @Scheduled(fixedRate = 2000)
    public void printLog() {
//        log.info("定时器执行开始。。。。");
        limitStrategyService.makeLimitValue();
//        log.info("定时器执行结束。。。。");
    }
}
