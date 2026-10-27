package com.android.settingslib.utils;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile Thread f1374a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile Handler f1375b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile ExecutorService f1376c;

    public static void a() {
        if (!d()) {
            throw new RuntimeException("Must be called on the UI thread");
        }
    }

    private static synchronized ExecutorService b() {
        if (f1376c == null) {
            f1376c = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }
        return f1376c;
    }

    public static Handler c() {
        if (f1375b == null) {
            f1375b = new Handler(Looper.getMainLooper());
        }
        return f1375b;
    }

    public static boolean d() {
        if (f1374a == null) {
            f1374a = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == f1374a;
    }

    public static Future e(Runnable runnable) {
        return b().submit(runnable);
    }

    public static void f(Runnable runnable) {
        c().post(runnable);
    }
}
