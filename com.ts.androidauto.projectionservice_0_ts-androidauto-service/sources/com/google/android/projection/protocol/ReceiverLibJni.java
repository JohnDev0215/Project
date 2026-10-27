package com.google.android.projection.protocol;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
class ReceiverLibJni {
    private static boolean isLoaded = false;

    private ReceiverLibJni() {
    }

    static synchronized void load() {
        if (!isLoaded && !"robolectric".equals(Build.FINGERPRINT)) {
            System.loadLibrary("autoreceiver_jni");
            isLoaded = true;
        }
    }
}
