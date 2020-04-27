package com.xieyy.boot.api;

import com.xieyy.boot.thread.TestRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@RestController
public class ThreadController {

    @Autowired
    private ExecutorService threadPoolExecutor;

    private static List<String> list = new ArrayList<>();

    static {
        for (int i = 0; i <= 1000; i++) {
            list.add("aaaa" + i);
        }
    }

    @GetMapping("/threadpool")
    public String threadPool() {
        TestRunnable testCallable = new TestRunnable();
        testCallable.setList(list);
        threadPoolExecutor.submit(testCallable);
        return "success";
    }
}
