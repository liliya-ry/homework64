package org.example.async;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.util.concurrent.Future;


@Component
public class A {
    @Async
    public void asyncMethod1() throws InterruptedException {
        Thread thread = Thread.currentThread();
        Thread.sleep(4);
        System.out.println("Execute method1 asynchronously " + thread);
    }

    @Async
    public void asyncMethod2() {
        Thread thread = Thread.currentThread();
        System.out.println("Execute method2 asynchronously " + thread);
    }

    @Async
    public void asyncThrowingMethod1() throws IllegalStateException {
        throw new IllegalStateException("some exception");
    }

    @Async
    public Future<String> asyncMethodWithReturnType1() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Execute method1 asynchronously - " + threadName);
        try {
            Thread.sleep(5);
            return new AsyncResult<>(threadName);
        } catch (InterruptedException ignored) {}
        return null;
    }

    @Async
    public Future<String> asyncMethodWithReturnType2() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Execute method2 asynchronously - " + threadName);
        try {
            Thread.sleep(5);
            return new AsyncResult<>(threadName);
        } catch (InterruptedException ignored) {}
        return null;
    }

    @Async
    public Future<String> asyncThrowingMethod2() throws IllegalStateException {
        throw new IllegalStateException("some exception");
    }
}
