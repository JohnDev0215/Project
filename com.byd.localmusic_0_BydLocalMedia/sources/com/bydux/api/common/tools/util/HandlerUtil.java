package com.bydux.api.common.tools.util;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes5.dex */
public class HandlerUtil {
    public static Handler mUiHandler = new Handler(Looper.getMainLooper());

    public static final void runOnUiThread(Runnable action) {
        Looper looperMyLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
        if (looperMyLooper != null && looperMyLooper == mainLooper) {
            action.run();
        } else {
            mUiHandler.post(action);
        }
    }

    public static final void runOnUiThreadDelay(Runnable action, long delayTime) {
        mUiHandler.postDelayed(action, delayTime);
    }
}
