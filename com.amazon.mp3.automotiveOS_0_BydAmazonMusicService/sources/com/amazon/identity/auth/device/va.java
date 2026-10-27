package com.amazon.identity.auth.device;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class va {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Executor f637a = new ja("MAPCommonThreadPool");
    public static final Object[] b = new Object[0];
    public static Thread c;
    public static Handler d;

    public static boolean a() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }

    public static void b(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static void a(Runnable runnable) {
        if (a()) {
            f637a.execute(runnable);
        } else {
            runnable.run();
        }
    }

    public static ka a(String str) {
        return new ka(str);
    }
}
