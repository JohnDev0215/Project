package com.byd.card.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class SingleThreadPoolUtils {
    private static ExecutorService pool;

    private static void init() {
        if (pool == null) {
            pool = Executors.newSingleThreadExecutor();
        }
    }

    public static void execute(Runnable r) {
        init();
        pool.execute(r);
    }

    public static void unInit() {
        ExecutorService executorService = pool;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        pool.shutdownNow();
        pool = null;
    }
}
