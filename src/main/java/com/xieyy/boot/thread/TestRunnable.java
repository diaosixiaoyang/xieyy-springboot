package com.xieyy.boot.thread;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestRunnable implements Runnable {

    @Setter
    private List<String> list;

    @Override
    public void run() {
        list.forEach((l) -> System.out.println(Thread.currentThread().getThreadGroup().getName() + Thread.currentThread().getName() + l));
    }
}
