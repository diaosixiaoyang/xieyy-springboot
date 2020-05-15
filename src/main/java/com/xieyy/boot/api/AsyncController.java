package com.xieyy.boot.api;

import com.xieyy.boot.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AsyncController {

    private AsyncService asyncService;

    @Autowired
    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }


    @GetMapping("/async")
    public String async() {
        log.info("async controller");
        String request = "async request";
//        asyncService.asyncTest(request);
        asyncTest(request);
        return request;
    }


    @Async
    public void asyncTest(String request) {
        log.info("async>>>>>>:{}", request);
    }

}
