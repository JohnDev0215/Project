package com.android.launcher3.util;

import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public class Preconditions {
    public static void assertNonUiThread() {
    }

    public static void assertNotNull(Object o) {
    }

    public static void assertUIThread() {
    }

    public static void assertWorkerThread() {
    }

    private static boolean isSameLooper(Looper looper) {
        return Looper.myLooper() == looper;
    }
}
