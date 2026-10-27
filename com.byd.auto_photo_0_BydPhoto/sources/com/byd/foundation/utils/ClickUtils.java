package com.byd.foundation.utils;

/* JADX INFO: loaded from: classes2.dex */
public class ClickUtils {
    private static final long DIFF = 200;
    private static final String TAG = "ClickUtils";
    private static int lastButtonId = -1;
    private static long lastClickTime = -1;

    public static boolean isFastDoubleClick(int i) {
        return isFastDoubleClick(i, DIFF);
    }

    public static boolean isFastDoubleClick(int i, long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (lastButtonId == i) {
            long j2 = lastClickTime;
            if (j2 > 0 && jCurrentTimeMillis - j2 < j) {
                LogUtils.e(TAG, "isFastDoubleClick");
                return true;
            }
        }
        lastClickTime = jCurrentTimeMillis;
        lastButtonId = i;
        return false;
    }

    public static boolean isFastDoubleClick(long j) {
        return isFastDoubleClick(-1, j);
    }
}
