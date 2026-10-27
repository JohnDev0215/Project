package com.byd.foundation.utils;

/* JADX INFO: loaded from: classes.dex */
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
        int i2 = lastButtonId;
        if (i2 == -1 || i == -1) {
            long j2 = lastClickTime;
            if (j2 > 0 && jCurrentTimeMillis - j2 < j) {
                LogUtils.e(TAG, "is FastDoubleClick any view");
                return true;
            }
        }
        if (i2 == i) {
            long j3 = lastClickTime;
            if (j3 > 0 && jCurrentTimeMillis - j3 < j) {
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
