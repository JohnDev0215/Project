package com.byd.card.floatcard;

import android.os.HandlerThread;
import android.os.Looper;
import com.byd.card.LooperExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class Executors {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int KEEP_ALIVE = 1;
    private static final int MAXIMUM_POOL_SIZE;
    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;
    public static final LooperExecutor MAIN_EXECUTOR = new LooperExecutor(Looper.getMainLooper());
    public static final LooperExecutor UI_HELPER_EXECUTOR = new LooperExecutor(createAndStartNewForegroundLooper("UiThreadHelper"));
    public static final LooperExecutor MODEL_EXECUTOR = new LooperExecutor(createAndStartNewLooper("card-loader"));

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = iAvailableProcessors;
        int i = iAvailableProcessors + 1;
        CORE_POOL_SIZE = i;
        int i2 = (iAvailableProcessors * 2) + 1;
        MAXIMUM_POOL_SIZE = i2;
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(i, i2, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static Looper createAndStartNewLooper(String name) {
        return createAndStartNewLooper(name, 0);
    }

    public static Looper createAndStartNewLooper(String name, int priority) {
        HandlerThread handlerThread = new HandlerThread(name, priority);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    public static Looper createAndStartNewForegroundLooper(String name) {
        return createAndStartNewLooper(name, -2);
    }
}
