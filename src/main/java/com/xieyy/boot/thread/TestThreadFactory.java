package com.xieyy.boot.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * implements ThreadFactory
 * 用于自定义线程工厂，然后进行设置名称等操作
 */
public class TestThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public TestThreadFactory(String groupName, String threadNamePrefix) {
        this.group = new ThreadGroup(groupName);
        SecurityManager s = System.getSecurityManager();
        this.namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-" + threadNamePrefix + "-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
