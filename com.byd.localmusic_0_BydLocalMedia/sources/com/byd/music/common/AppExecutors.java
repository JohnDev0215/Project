package com.byd.music.common;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.content.NativeLibraryHelper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes5.dex */
public class AppExecutors {
    private static AppExecutors sAppExecutors = new AppExecutors();
    private final Executor mDiskIO = Executors.newSingleThreadExecutor(new MyThreadFactory("single"));
    private final Executor mNetworkIO = Executors.newFixedThreadPool(3, new MyThreadFactory(Camera.Parameters.FOCUS_MODE_FIXED));
    private final MainThreadExecutor mMainThread = new MainThreadExecutor();
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3, new MyThreadFactory("sc"), new ThreadPoolExecutor.AbortPolicy());

    public interface DelayExecutor {
        void executeDelay(Runnable runnable, long j);
    }

    public static AppExecutors getInstance() {
        return sAppExecutors;
    }

    class MyThreadFactory implements ThreadFactory {
        private long count = 0;
        private final String name;

        MyThreadFactory(String str) {
            this.name = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            this.count++;
            return new Thread(runnable, this.name + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + this.count + "-Thread");
        }
    }

    public Executor diskIO() {
        return this.mDiskIO;
    }

    public ScheduledThreadPoolExecutor schedule() {
        return this.executor;
    }

    public Executor networkIO() {
        return this.mNetworkIO;
    }

    public Executor mainThread() {
        return this.mMainThread;
    }

    public DelayExecutor mainThreadDelay() {
        return this.mMainThread;
    }

    private static class MainThreadExecutor implements Executor, DelayExecutor {
        private Handler mainThreadHandler;

        private MainThreadExecutor() {
            this.mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mainThreadHandler.post(runnable);
        }

        @Override // com.byd.music.common.AppExecutors.DelayExecutor
        public void executeDelay(Runnable runnable, long j) {
            this.mainThreadHandler.postDelayed(runnable, j);
        }
    }
}
