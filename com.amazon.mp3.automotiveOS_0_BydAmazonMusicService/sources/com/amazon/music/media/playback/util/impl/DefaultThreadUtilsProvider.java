package com.amazon.music.media.playback.util.impl;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import androidx.annotation.NonNull;
import com.amazon.music.media.playback.util.RunnableCaller;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultThreadUtilsProvider implements ThreadUtilsProvider {
    private ScheduledThreadPoolExecutor backgroundExecutor;
    private Handler mainHandler;
    private static final Logger logger = LoggerFactory.getLogger("ThreadUtils");
    private static final long THREAD_KEEP_ALIVE_TIME = TimeUnit.MINUTES.toMillis(1);
    private final Object lock = new Object();
    private final AtomicInteger threadIndexCounter = new AtomicInteger(1);
    private final Executor mainThreadExecutor = new Executor() { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.1
        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            DefaultThreadUtilsProvider.this.getMainHandler().post(runnable);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public Handler getMainHandler() {
        if (this.mainHandler == null) {
            synchronized (this.lock) {
                if (this.mainHandler == null) {
                    this.mainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return this.mainHandler;
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public ScheduledExecutorService getBackgroundExecutor() {
        if (this.backgroundExecutor == null) {
            synchronized (this.lock) {
                if (this.backgroundExecutor == null) {
                    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(16, new ThreadFactory() { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.2
                        @Override // java.util.concurrent.ThreadFactory
                        public Thread newThread(@NonNull final Runnable runnable) {
                            return new Thread("ThreadUtils-" + DefaultThreadUtilsProvider.this.threadIndexCounter.getAndIncrement()) { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.2.1
                                @Override // java.lang.Thread, java.lang.Runnable
                                public void run() {
                                    Process.setThreadPriority(10);
                                    runnable.run();
                                }
                            };
                        }
                    }) { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.3
                        @Override // java.util.concurrent.ThreadPoolExecutor
                        protected void afterExecute(Runnable runnable, Throwable th) {
                            if (th != null) {
                                DefaultThreadUtilsProvider.logger.error("Error in background thread", th);
                            }
                        }
                    };
                    this.backgroundExecutor = scheduledThreadPoolExecutor;
                    scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
                }
            }
        }
        return this.backgroundExecutor;
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public ExecutorService createBackgroundExecutor(final String str) {
        return new ThreadPoolExecutor(0, 1, THREAD_KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.4
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(@NonNull final Runnable runnable) {
                return new Thread(str) { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.4.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        Process.setThreadPriority(10);
                        runnable.run();
                    }
                };
            }
        }) { // from class: com.amazon.music.media.playback.util.impl.DefaultThreadUtilsProvider.5
            @Override // java.util.concurrent.ThreadPoolExecutor
            protected void afterExecute(Runnable runnable, Throwable th) {
                if (th != null) {
                    DefaultThreadUtilsProvider.logger.error("Error in background thread", th);
                }
            }
        };
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public Executor getMainThreadExecutor() {
        return this.mainThreadExecutor;
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public boolean isOnMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public void postOnMainThread(@NonNull Runnable runnable) {
        getMainHandler().post(runnable);
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public void postOnMainThreadDelayed(@NonNull Runnable runnable, long j) {
        getMainHandler().postDelayed(runnable, j);
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public void cancelOnMainThread(@NonNull Runnable runnable) {
        getMainHandler().removeCallbacks(runnable);
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public void postInBackground(@NonNull Runnable runnable) {
        getBackgroundExecutor().schedule(new RunnableCaller(runnable), 0L, TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.music.media.playback.util.impl.ThreadUtilsProvider
    public <T> ScheduledFuture<T> postInBackgroundDelayed(@NonNull Callable<T> callable, long j) {
        return getBackgroundExecutor().schedule(callable, j, TimeUnit.MILLISECONDS);
    }
}
