package org.example.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;
import java.util.concurrent.*;

@Configuration
@EnableAsync
@ComponentScan("org.example.async")
public class AsyncConfig implements AsyncConfigurer {
    @Override
    @Bean("executor")
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(5);
    }

    @Override
    @Bean("exceptionHandler")
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }
}
