package com.tester.jvm.mock.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 通用线程池（非执行单一任务）
 * <p>
 *
 * @author zhaoyb1990
 */
public class ExecutorInner {

    private static ExecutorService executor = new ThreadPoolExecutor( Runtime.getRuntime().availableProcessors() - 1,
        4 * Runtime.getRuntime().availableProcessors(),
        30L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(4096),
        new BasicThreadFactory.Builder().namingPattern("mock-common-pool-%d").build(),
        new ThreadPoolExecutor.CallerRunsPolicy());

    public static void execute(Runnable r) {
        executor.execute(r);
    }

    public static Executor getExecutor() {
        return executor;
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        return executor.submit(callable);
    }
}
