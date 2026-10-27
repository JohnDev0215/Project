package com.byd.music.utils;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes5.dex */
public class DoubleClickHelper {
    private static final long[] TIME_ARRAY = new long[2];

    public static boolean isOnDoubleClick() {
        return isOnDoubleClick(2500);
    }

    public static boolean isOnDoubleClick(int i) {
        long[] jArr = TIME_ARRAY;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = TIME_ARRAY;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        return TIME_ARRAY[0] >= SystemClock.uptimeMillis() - ((long) i);
    }
}
