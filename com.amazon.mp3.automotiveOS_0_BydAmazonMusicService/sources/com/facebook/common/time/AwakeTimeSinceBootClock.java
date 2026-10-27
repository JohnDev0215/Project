package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;

/* JADX INFO: loaded from: classes5.dex */
@DoNotStrip
public class AwakeTimeSinceBootClock implements MonotonicClock, MonotonicNanoClock {

    @DoNotStrip
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    private AwakeTimeSinceBootClock() {
    }

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // com.facebook.common.time.MonotonicClock
    @DoNotStrip
    public long now() {
        return android.os.SystemClock.uptimeMillis();
    }

    @Override // com.facebook.common.time.MonotonicNanoClock
    @DoNotStrip
    public long nowNanos() {
        return System.nanoTime();
    }
}
