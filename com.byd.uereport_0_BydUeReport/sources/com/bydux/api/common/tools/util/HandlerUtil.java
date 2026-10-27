package com.bydux.api.common.tools.util;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public class HandlerUtil {
    public static Handler mUiHandler = new Handler(Looper.getMainLooper());

    public static final void runOnUiThread(Runnable runnable) {
        Looper looperMyLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
        if (looperMyLooper == null || looperMyLooper != mainLooper) {
            mUiHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public static final void runOnUiThreadDelay(Runnable runnable, long j8) {
        mUiHandler.postDelayed(runnable, j8);
    }
}
