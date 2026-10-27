package org.chromium.base;

import J.N;
import android.os.Process;
import android.os.SystemClock;
import defpackage.Hs;
import defpackage.Ks;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EarlyTraceEvent {
    public static final Object a = new Object();
    public static volatile int b;
    public static List c;
    public static Map d;
    public static List e;
    public static List f;

    public static void a(String str) {
        if (b()) {
            Process.myTid();
            SystemClock.elapsedRealtimeNanos();
            SystemClock.currentThreadTimeMillis();
            synchronized (a) {
                if (b()) {
                    e(str);
                    throw null;
                }
            }
        }
    }

    public static boolean b() {
        return b == 1;
    }

    public static void c(String str) {
        if (d()) {
            synchronized (a) {
                if (d()) {
                    e(str);
                    throw null;
                }
            }
        }
    }

    public static boolean d() {
        int i = b;
        return i == 1 || i == 2;
    }

    public static String e(String str) {
        return str + "@" + Process.myTid();
    }

    public static void f() {
        if (!c.isEmpty()) {
            List<Ks> list = c;
            long jMklbOJun = (N.MklbOJun() * 1000) - SystemClock.elapsedRealtimeNanos();
            for (Ks ks : list) {
                N.MDy_VEWN(ks.a, ks.c + jMklbOJun, ks.e + jMklbOJun, ks.b, ks.f - ks.d);
            }
            c.clear();
        }
        if (!e.isEmpty()) {
            List list2 = e;
            N.MklbOJun();
            SystemClock.elapsedRealtimeNanos();
            Iterator it = list2.iterator();
            if (it.hasNext()) {
                throw null;
            }
            e.clear();
        }
        if (d.isEmpty() && f.isEmpty()) {
            b = 3;
            d = null;
            c = null;
            f = null;
            e = null;
        }
    }

    public static boolean getBackgroundStartupTracingFlag() {
        return false;
    }

    public static void setBackgroundStartupTracingFlag(boolean z) {
        Hs.a.edit().putBoolean("bg_startup_tracing", z).apply();
    }
}
