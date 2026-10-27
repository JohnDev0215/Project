package com.amazon.music.media.playback.util;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class RateLimiter {
    private Runnable callback;
    private final long incompleteDataDelay;
    private final Runnable internalRunner = new Runnable() { // from class: com.amazon.music.media.playback.util.RateLimiter.1
        @Override // java.lang.Runnable
        public void run() {
            RateLimiter.this.onUpdated();
            RateLimiter.this.callback.run();
        }
    };
    private long lastCallbackTime;
    private final long minSpacing;
    private long pendingTime;

    public RateLimiter(@NonNull Runnable runnable, long j, long j2) {
        this.callback = (Runnable) Validate.notNull(runnable);
        Validate.exclusiveBetween(0L, Long.MAX_VALUE, j);
        Validate.inclusiveBetween(0L, Long.MAX_VALUE, j2);
        this.minSpacing = j;
        this.incompleteDataDelay = j2;
    }

    public void onDataChanged(boolean z) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jMax = Math.max(this.lastCallbackTime + this.minSpacing, (z ? 0L : this.incompleteDataDelay) + jElapsedRealtime);
        long j = this.pendingTime;
        if (j <= 0 || j > jMax) {
            if (j > 0) {
                ThreadUtils.cancelOnMainThread(this.internalRunner);
            }
            this.pendingTime = jMax;
            ThreadUtils.postOnMainThreadDelayed(this.internalRunner, jMax - jElapsedRealtime);
        }
    }

    public void onUpdated() {
        this.lastCallbackTime = SystemClock.elapsedRealtime();
        this.pendingTime = 0L;
        ThreadUtils.cancelOnMainThread(this.internalRunner);
    }
}
