package com.byd.vrassistant.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public class ExecutorUtils {
    private static ExecutorService mExcecutorSevice;
    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    private ExecutorUtils() {
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            mHandler.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        mHandler.postDelayed(runnable, j);
    }

    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    private static synchronized ExecutorService getThreadPool() {
        if (mExcecutorSevice == null) {
            mExcecutorSevice = new ThreadPoolExecutor(0, 128, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new BydThreadFactory("cached"));
        }
        return mExcecutorSevice;
    }

    public static void shutdown() {
        ExecutorService executorService = mExcecutorSevice;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private static final class BydThreadFactory extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final String namePrefix;

        BydThreadFactory(String str) {
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.namePrefix + getAndIncrement());
        }
    }
}
