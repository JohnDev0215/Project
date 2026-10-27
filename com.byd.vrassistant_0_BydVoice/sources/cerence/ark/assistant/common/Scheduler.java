package cerence.ark.assistant.common;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class Scheduler {
    private static ExecutorService CACHED_EXECUTOR;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private Scheduler() {
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static Handler getMainHandler() {
        return HANDLER;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    public static void executeByCached(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    private static synchronized ExecutorService getThreadPool() {
        if (CACHED_EXECUTOR == null) {
            CACHED_EXECUTOR = new ThreadPoolExecutor(0, 128, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ArkThreadFactory("cached"));
        }
        return CACHED_EXECUTOR;
    }

    private static final class ArkThreadFactory extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final String namePrefix;

        ArkThreadFactory(String str) {
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.namePrefix + getAndIncrement());
        }
    }
}
