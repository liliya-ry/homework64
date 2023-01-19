package org.example.async;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.concurrent.*;

class AsyncTest {
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(AsyncConfig.class);
    }

    @Test
    @DisplayName("Async void methods - test time")
    void test1() throws ExecutionException, InterruptedException {
        A a = context.getBean(A.class);
        assertNotNull(a);

        FutureTask<Long> futureTask1 = getFutureTask(a::asyncMethod1);
        FutureTask<Long> futureTask2 = getFutureTask(a::asyncMethod2);

        long startTime2 = System.nanoTime();
        futureTask1.run();

        futureTask2.run();
        long finishTime1 = futureTask1.get();

        assertTrue(startTime2 < finishTime1);
    }

    FutureTask<Long> getFutureTask(Executable executable) {
        Callable<Long> callable = () -> {
            try {
                executable.execute();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            return System.nanoTime();
        };
        return new FutureTask<>(callable);
    }

    @Test
    @DisplayName("Test executor creation")
    void test2() {
        Executor executor = (Executor) context.getBean("executor");
        assertNotNull(executor);
    }

    @Test
    @DisplayName("Async Future methods - check different threads")
    void test3() throws ExecutionException, InterruptedException {
        A a = context.getBean(A.class);
        assertNotNull(a);

        Future<String> future1 = a.asyncMethodWithReturnType1();
        Future<String> future2 = a.asyncMethodWithReturnType1();
        assertNotEquals(future1.get(), future2.get());
    }

    @Test
    @DisplayName("Async Future methods - exceptions")
    void test4() {
        A a = context.getBean(A.class);
        assertNotNull(a);

        Future<String> future = a.asyncThrowingMethod2();
        assertThrows(ExecutionException.class, future::get, "some exception");
    }

    @Test
    @DisplayName("Async void methods - exceptions")
    void test5() {
        A a = context.getBean(A.class);
        assertNotNull(a);
        CustomAsyncExceptionHandler excHandler = (CustomAsyncExceptionHandler) context.getBean("exceptionHandler");
        assertNotNull(excHandler);
        a.asyncThrowingMethod1();
    }
}
