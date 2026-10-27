package com.amazon.music.push.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public final class PushSchedulers {
    private static ExecutorService executorService;
    private static Scheduler scheduler;

    private PushSchedulers() {
    }

    public static synchronized Scheduler getPushScheduler() {
        if (scheduler == null) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.DiscardPolicy());
            executorService = threadPoolExecutor;
            scheduler = Schedulers.from(threadPoolExecutor);
        }
        return scheduler;
    }
}
