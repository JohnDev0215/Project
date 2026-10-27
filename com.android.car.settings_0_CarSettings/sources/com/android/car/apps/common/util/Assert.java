package com.android.car.apps.common.util;

import android.os.Looper;
import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public class Assert {
    private static final String TAG = "Em.MediaAssert";

    private Assert() {
    }

    public static void isMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            fail("Expected to run on main thread.");
        }
    }

    public static void fail(String message) {
        Log.e(TAG, "Assert.fail() called: " + message);
        throw new AssertionError(message);
    }
}
