package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;

/* JADX INFO: loaded from: classes5.dex */
@DoNotStrip
public class RealtimeSinceBootClock implements MonotonicClock {
    private static final RealtimeSinceBootClock INSTANCE = new RealtimeSinceBootClock();

    private RealtimeSinceBootClock() {
    }

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return INSTANCE;
    }

    @Override // com.facebook.common.time.MonotonicClock
    public long now() {
        return android.os.SystemClock.elapsedRealtime();
    }
}
