package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import defpackage.aw;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class ThreadUtils {
    public static final Object a = new Object();
    public static Handler b;

    public static class a {
        public a() {
            Process.myTid();
        }
    }

    public static void b() {
        if (!g()) {
            throw new IllegalStateException("Must be called on the UI thread.");
        }
    }

    public static Handler c() {
        boolean z;
        synchronized (a) {
            if (b == null) {
                b = new Handler(Looper.getMainLooper());
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            TraceEvent.h.set(true);
            TraceEvent.a aVar = TraceEvent.j;
            if (aVar != null) {
                aVar.j.set(true);
                if (g()) {
                    if (!aVar.n) {
                        Looper.myQueue().addIdleHandler(aVar);
                        aVar.n = true;
                    }
                    aVar.b();
                } else {
                    e(new aw(3, aVar));
                }
            }
            if (TraceEvent.f) {
                TraceEvent.f.b();
            }
        }
        return b;
    }

    public static Looper d() {
        return c().getLooper();
    }

    @Deprecated
    public static void e(Runnable runnable) {
        c().post(runnable);
    }

    @Deprecated
    public static void f(Runnable runnable) {
        if (g()) {
            runnable.run();
        } else {
            c().post(runnable);
        }
    }

    public static boolean g() {
        return c().getLooper() == Looper.myLooper();
    }

    @CalledByNative
    public static boolean isThreadPriorityAudio(int i) {
        return Process.getThreadPriority(i) == -16;
    }

    @CalledByNative
    public static void setThreadPriorityAudio(int i) {
        Process.setThreadPriority(i, -16);
    }

    public static void a() {
    }
}
