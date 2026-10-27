package com.amazon.mp3.util;

import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes4.dex */
public class ForegroundLogger {
    public static final String TAG = "ForegroundLogger";

    public static void log(Fragment fragment) {
        log(fragment.getClass().getSimpleName());
    }

    private static void log(String name) {
        Log.info(TAG, "%s displayed", name);
    }
}
