package com.amazon.bolthttp.internal.util;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes2.dex */
public final class TimeUtils {
    public static long getTimeMillis() {
        return SystemClock.elapsedRealtime();
    }

    public static void sleep(long j) throws InterruptedException {
        if (j > 0) {
            Thread.sleep(j);
        }
    }
}
