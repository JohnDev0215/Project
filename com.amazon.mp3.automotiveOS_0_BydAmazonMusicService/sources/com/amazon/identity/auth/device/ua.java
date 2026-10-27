package com.amazon.identity.auth.device;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ua extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        va.d = new Handler();
        Looper.loop();
    }
}
