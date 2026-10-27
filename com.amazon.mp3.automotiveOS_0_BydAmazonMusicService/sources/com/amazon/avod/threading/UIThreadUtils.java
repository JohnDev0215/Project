package com.amazon.avod.threading;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.amazon.avod.perf.Profiler;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class UIThreadUtils {
    private static final Handler POST_TO_MAIN_LOOPER = new Handler(Looper.getMainLooper());

    public static boolean postToUIThread(@Nonnull ProfiledRunnable profiledRunnable) {
        Preconditions.checkNotNull(profiledRunnable, "profiledRunnable");
        return POST_TO_MAIN_LOOPER.post(profiledRunnable);
    }

    public static boolean postToUIThreadWithToken(@Nonnull ProfiledRunnable profiledRunnable, @Nonnull Object obj) {
        Preconditions.checkNotNull(profiledRunnable, "profiledRunnable");
        Preconditions.checkNotNull(obj, "token");
        return POST_TO_MAIN_LOOPER.postAtTime(profiledRunnable, obj, SystemClock.uptimeMillis());
    }

    public static void removeCallbacksAndMessages(@Nonnull Object obj) {
        Preconditions.checkNotNull(obj, "token");
        POST_TO_MAIN_LOOPER.removeCallbacksAndMessages(obj);
    }

    public static boolean reportException(@Nonnull RuntimeException runtimeException) {
        Preconditions.checkNotNull(runtimeException, "exception");
        return postToUIThread(new ProfiledRunnable(new ThrowExceptionRunnable(runtimeException), Profiler.TraceLevel.INFO, "%s:reportException", UIThreadUtils.class.getSimpleName()));
    }

    private static class ThrowExceptionRunnable implements Runnable {
        private final RuntimeException mException;

        ThrowExceptionRunnable(@Nonnull RuntimeException runtimeException) {
            this.mException = runtimeException;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw this.mException;
        }
    }
}
