package com.byd.bluetoothcall.e;

import android.os.HandlerThread;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Executors.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f3268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3269b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3270c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadPoolExecutor f3271d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f3272e = new b(Looper.getMainLooper());
    public static final b f = new b(a("UiThreadHelper"));
    public static final b g = new b(b("card-loader"));

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        f3269b = iAvailableProcessors;
        int i = iAvailableProcessors + 1;
        f3268a = i;
        int i2 = (iAvailableProcessors * 2) + 1;
        f3270c = i2;
        f3271d = new ThreadPoolExecutor(i, i2, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static Looper a(String str) {
        return c(str, -2);
    }

    public static Looper b(String str) {
        return c(str, 0);
    }

    public static Looper c(String str, int i) {
        HandlerThread handlerThread = new HandlerThread(str, i);
        handlerThread.start();
        return handlerThread.getLooper();
    }
}
