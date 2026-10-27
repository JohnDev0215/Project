package com.amplifyframework.api.aws;

import android.os.Handler;
import android.os.Looper;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;

/* JADX INFO: loaded from: classes5.dex */
final class TimeoutWatchdog {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long timeoutMs = -1;
    private Runnable timeoutAction = null;

    TimeoutWatchdog() {
    }

    synchronized void start(Runnable runnable, long j) throws ApiException {
        if (runnable == null) {
            throw new ApiException("Passed null action to watchdog.", AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
        if (j <= 0) {
            throw new ApiException("timeoutMs must be > 0.", "Make sure you didn't set a negative timeout");
        }
        stop();
        this.timeoutMs = j;
        this.timeoutAction = runnable;
        this.handler.postDelayed(runnable, j);
    }

    synchronized void reset() {
        Runnable runnable = this.timeoutAction;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            this.handler.postDelayed(this.timeoutAction, this.timeoutMs);
        }
    }

    synchronized void stop() {
        Runnable runnable = this.timeoutAction;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        this.timeoutAction = null;
        this.timeoutMs = -1L;
    }
}
