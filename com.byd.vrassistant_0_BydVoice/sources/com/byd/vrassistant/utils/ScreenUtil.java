package com.byd.vrassistant.utils;

import com.byd.vrassistant.ContextHolder;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenUtil {
    private static final String TAG = "NEU_ScreenUtil";

    public static int getSkinType() {
        int i = ContextHolder.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        RLog.i(TAG, "getSkinType nightMode= " + i);
        int i2 = i == 32 ? 2 : 1;
        RLog.i(TAG, "getSkinType skinType= " + i2);
        return i2;
    }

    public static boolean isLandscape() {
        return ContextHolder.getApplicationContext().getResources().getConfiguration().orientation == 2;
    }
}
