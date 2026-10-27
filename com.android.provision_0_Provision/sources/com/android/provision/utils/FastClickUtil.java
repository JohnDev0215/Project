package com.android.provision.utils;

import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class FastClickUtil {
    private static final long CLICK_INTERVAL = 200;
    private static final String TAG = "FastClickUtil";
    protected static long mLastClickTime;

    public static boolean isFastClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - mLastClickTime < CLICK_INTERVAL) {
            BydLogHelper.logW(TAG, "FastClick");
            return true;
        }
        mLastClickTime = jCurrentTimeMillis;
        return false;
    }
}
