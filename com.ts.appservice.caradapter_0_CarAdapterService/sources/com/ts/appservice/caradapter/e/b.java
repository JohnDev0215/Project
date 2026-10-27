package com.ts.appservice.caradapter.e;

import android.os.HandlerThread;
import android.util.ArrayMap;
import android.util.Log;

/* JADX INFO: compiled from: AdapterUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ArrayMap<String, HandlerThread> f84a = new ArrayMap<>();

    public static HandlerThread a(String str) {
        HandlerThread handlerThread;
        synchronized (f84a) {
            handlerThread = f84a.get(str);
            if (handlerThread == null || !handlerThread.isAlive()) {
                Log.i("CAR.ADAPTER.UTIL", "Starting HandlerThread:" + str);
                handlerThread = new HandlerThread(str);
                handlerThread.start();
                f84a.put(str, handlerThread);
            }
        }
        return handlerThread;
    }
}
