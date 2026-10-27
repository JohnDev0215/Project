package com.byd.vrassistant.utils;

/* JADX INFO: loaded from: classes2.dex */
public class FastClickUtils {
    private static final long FAST_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    public static boolean isValuableClick() {
        if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }
}
