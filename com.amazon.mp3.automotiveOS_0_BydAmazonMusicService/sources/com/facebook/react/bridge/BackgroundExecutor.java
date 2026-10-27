package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class BackgroundExecutor {
    private static final String TAG = "FabricBackgroundExecutor";
    private final ExecutorService mExecutorService;

    private static class NamedThreadFactory implements ThreadFactory {
        private final String mName;

        public NamedThreadFactory(String str) {
            this.mName = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread threadNewThread = Executors.defaultThreadFactory().newThread(runnable);
            threadNewThread.setName(this.mName);
            return threadNewThread;
        }
    }

    @DoNotStrip
    private BackgroundExecutor(String str) {
        this.mExecutorService = Executors.newFixedThreadPool(1, new NamedThreadFactory(str));
    }

    @DoNotStrip
    private void queueRunnable(Runnable runnable) {
        if (runnable == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("runnable is null"));
            return;
        }
        ExecutorService executorService = this.mExecutorService;
        if (executorService == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("executorService is null"));
        } else {
            executorService.execute(runnable);
        }
    }
}
