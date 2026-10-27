package com.android.inputmethod.latin.utils;

import android.util.Log;
import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class ExecutorUtils {
    private static final String TAG = "ExecutorUtils";
    private static ScheduledExecutorService sExecutorServiceForTests;
    public static final String KEYBOARD = "Keyboard";
    private static ScheduledExecutorService sKeyboardExecutorService = newExecutorService(KEYBOARD);
    public static final String SPELLING = "Spelling";
    private static ScheduledExecutorService sSpellingExecutorService = newExecutorService(SPELLING);

    private static ScheduledExecutorService newExecutorService(final String name) {
        return Executors.newSingleThreadScheduledExecutor(new ExecutorFactory(name));
    }

    private static class ExecutorFactory implements ThreadFactory {
        private final String mName;

        private ExecutorFactory(final String name) {
            this.mName = name;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(final Runnable runnable) {
            Thread thread = new Thread(runnable, ExecutorUtils.TAG);
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.android.inputmethod.latin.utils.ExecutorUtils.ExecutorFactory.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread2, Throwable ex) {
                    Log.w(ExecutorFactory.this.mName + "-" + runnable.getClass().getSimpleName(), ex);
                }
            });
            return thread;
        }
    }

    public static void setExecutorServiceForTests(final ScheduledExecutorService executorServiceForTests) {
        sExecutorServiceForTests = executorServiceForTests;
    }

    public static ScheduledExecutorService getBackgroundExecutor(final String name) {
        ScheduledExecutorService scheduledExecutorService = sExecutorServiceForTests;
        if (scheduledExecutorService != null) {
            return scheduledExecutorService;
        }
        name.hashCode();
        if (name.equals(SPELLING)) {
            return sSpellingExecutorService;
        }
        if (name.equals(KEYBOARD)) {
            return sKeyboardExecutorService;
        }
        throw new IllegalArgumentException("Invalid executor: " + name);
    }

    public static void killTasks(final String name) {
        ScheduledExecutorService backgroundExecutor = getBackgroundExecutor(name);
        backgroundExecutor.shutdownNow();
        try {
            backgroundExecutor.awaitTermination(5L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Log.wtf(TAG, "Failed to shut down: " + name);
        }
        if (backgroundExecutor == sExecutorServiceForTests) {
            return;
        }
        name.hashCode();
        if (name.equals(SPELLING)) {
            sSpellingExecutorService = newExecutorService(SPELLING);
        } else {
            if (name.equals(KEYBOARD)) {
                sKeyboardExecutorService = newExecutorService(KEYBOARD);
                return;
            }
            throw new IllegalArgumentException("Invalid executor: " + name);
        }
    }

    public static Runnable chain(final Runnable... runnables) {
        return new RunnableChain(runnables);
    }

    public static class RunnableChain implements Runnable {
        private final Runnable[] mRunnables;

        private RunnableChain(final Runnable... runnables) {
            if (runnables == null || runnables.length == 0) {
                throw new IllegalArgumentException("Attempting to construct an empty chain");
            }
            this.mRunnables = runnables;
        }

        public Runnable[] getRunnables() {
            return this.mRunnables;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (Runnable runnable : this.mRunnables) {
                if (Thread.interrupted()) {
                    return;
                }
                runnable.run();
            }
        }
    }
}
