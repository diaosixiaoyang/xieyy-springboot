package com.xieyy.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
//@EnableScheduling
public class XieyySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(XieyySpringbootApplication.class, args);
        log.info("xieyy启动成功！！！");
    }

}
