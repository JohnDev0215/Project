package com.qtxa.qtxadefend;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
final class f extends HandlerThread {
    f(String str) {
        super(str);
    }

    void a(Runnable runnable) {
        new Handler(getLooper()).post(runnable);
    }
}
