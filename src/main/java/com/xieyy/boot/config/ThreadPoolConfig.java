package com.xieyy.boot.config;

import com.xieyy.boot.thread.TestRejected;
import com.xieyy.boot.thread.TestRunnable;
import com.xieyy.boot.thread.TestThreadFactory;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
@ConfigurationProperties(prefix = "xieyy.thread")
public class ThreadPoolConfig {

    @Setter
    private int corePoolSize;
    @Setter
    private int maximumPoolSize;
    @Setter
    private long keepAliveTime;
    @Setter
    private int queueSize;

    @Bean
    public ExecutorService threadPoolExecutor() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueSize));
        ThreadFactory factory = new TestThreadFactory("xieyy", "xyyp");
        TestRejected testRejected = new TestRejected();
        executorService.setRejectedExecutionHandler(testRejected);
        testRejected.rejectedExecution(new TestRunnable(), executorService);
        executorService.setThreadFactory(factory);
        return executorService;
    }
}
