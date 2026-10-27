package com.amazon.identity.mobi.common.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class ThreadUtils {
    private static Handler sMapLooperHandler;
    private static Thread sMapLooperThread;
    static final Map<String, Executor> MAP_BACKGROUND_SERIAL_THREAD_POOL = new HashMap();
    private static final Executor MAP_COMMON_THREAD_POOL = Executors.newCachedThreadPool(new NamedThreadFactory("MAPCommonThreadPool"));
    private static final Object[] LOCK = new Object[0];

    static final class NamedThreadFactory implements ThreadFactory {
        private final String mName;
        private final AtomicInteger mThreadNum;

        public String getName() {
            return this.mName;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, String.format(Locale.US, "%s#%d", this.mName, Integer.valueOf(this.mThreadNum.getAndIncrement())));
        }

        private NamedThreadFactory(String str) {
            this.mThreadNum = new AtomicInteger(1);
            this.mName = str;
        }
    }

    private ThreadUtils() {
    }

    public static Handler getMapLooperHandler() {
        synchronized (LOCK) {
            Handler handler = sMapLooperHandler;
            if (handler != null) {
                return handler;
            }
            Thread thread = new Thread() { // from class: com.amazon.identity.mobi.common.utils.ThreadUtils.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Looper.prepare();
                    Handler unused = ThreadUtils.sMapLooperHandler = new Handler();
                    Looper.loop();
                }
            };
            sMapLooperThread = thread;
            thread.start();
            return sMapLooperHandler;
        }
    }

    public static NamedThreadFactory getNamedThreadFactory(String str) {
        return new NamedThreadFactory(str);
    }

    public static boolean isRunningOnMainThread() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }

    public static void runOffMainThread(Runnable runnable) {
        if (isRunningOnMainThread()) {
            submitToBackgroundThread(runnable);
        } else {
            runnable.run();
        }
    }

    public static void runOnMainThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static synchronized void submitToBackgroundSerialThread(String str, Runnable runnable) {
        Map<String, Executor> map = MAP_BACKGROUND_SERIAL_THREAD_POOL;
        Executor executorNewSingleThreadExecutor = map.get(str);
        if (executorNewSingleThreadExecutor == null) {
            executorNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
            map.put(str, executorNewSingleThreadExecutor);
        }
        executorNewSingleThreadExecutor.execute(runnable);
    }

    public static void submitToBackgroundThread(Runnable runnable) {
        MAP_COMMON_THREAD_POOL.execute(runnable);
    }
}
