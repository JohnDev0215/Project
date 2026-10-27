package com.android.systemui.util;

import android.os.SystemClock;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FastClickUtil {
    private static long DIFF = 300;
    private static int lastButtonId = -1;
    private static Map<Integer, Long> mLastClickMap = new HashMap();
    private static long mLastClickTime;

    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    public static boolean isFastDoubleClick(int buttonId, long clickTime) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long jLongValue = mLastClickMap.containsKey(Integer.valueOf(buttonId)) ? mLastClickMap.get(Integer.valueOf(buttonId)).longValue() : 0L;
        long j = jUptimeMillis - jLongValue;
        if (lastButtonId == buttonId && jLongValue > 0 && Math.abs(j) < clickTime) {
            return true;
        }
        mLastClickMap.put(Integer.valueOf(buttonId), Long.valueOf(jUptimeMillis));
        lastButtonId = buttonId;
        return false;
    }

    public static boolean isFastDoubleClick(long clickTime) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long j = mLastClickTime;
        long j2 = jUptimeMillis - j;
        if (j > 0 && Math.abs(j2) < clickTime) {
            return true;
        }
        mLastClickTime = jUptimeMillis;
        return false;
    }
}
