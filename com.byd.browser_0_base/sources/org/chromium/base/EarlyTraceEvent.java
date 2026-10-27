package org.chromium.base;

import J.N;
import android.os.Process;
import android.os.SystemClock;
import defpackage.iv;
import defpackage.xz;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class EarlyTraceEvent {
    public static volatile int a;
    public static volatile boolean b;
    public static boolean c;
    public static final Object d = new Object();
    public static ArrayList e;
    public static ArrayList f;

    public static final class a {
        public final boolean a;
        public final String b;
        public final long c;
        public final long d = System.nanoTime();

        public a(long j, String str, boolean z) {
            this.b = str;
            this.c = j;
            this.a = z;
        }
    }

    public static final class b {
        public final boolean a;
        public final boolean b;
        public final String c;
        public final int d = Process.myTid();
        public final long e = System.nanoTime();
        public final long f = SystemClock.currentThreadTimeMillis();

        public b(String str, boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
            this.c = str;
        }
    }

    public static void a(String str, boolean z) {
        if (e()) {
            b bVar = new b(str, true, z);
            synchronized (d) {
                if (e()) {
                    e.add(bVar);
                }
            }
        }
    }

    public static void b() {
        synchronized (d) {
            if (e()) {
                if (!e.isEmpty()) {
                    c(e);
                    e.clear();
                }
                if (!f.isEmpty()) {
                    for (a aVar : f) {
                        boolean z = aVar.a;
                        long j = aVar.d;
                        long j2 = aVar.c;
                        String str = aVar.b;
                        if (z) {
                            N.M_Gv8TwM(str, j2, j);
                        } else {
                            N.MrKsqeCD(str, j2, j);
                        }
                    }
                    f.clear();
                }
                a = 2;
                e = null;
                f = null;
            }
        }
    }

    public static void c(List<b> list) {
        for (b bVar : list) {
            boolean z = bVar.a;
            boolean z2 = bVar.b;
            if (z) {
                if (z2) {
                    N.M7UXCmoq(bVar.c, bVar.e, bVar.d, bVar.f);
                } else {
                    N.MrWG2uUW(bVar.c, bVar.e, bVar.d, bVar.f);
                }
            } else if (z2) {
                N.MRlw2LEn(bVar.c, bVar.e, bVar.d, bVar.f);
            } else {
                N.MmyrhqXB(bVar.c, bVar.e, bVar.d, bVar.f);
            }
        }
    }

    public static void d() {
        synchronized (d) {
            if (a != 0) {
                return;
            }
            e = new ArrayList();
            f = new ArrayList();
            a = 1;
        }
    }

    public static boolean e() {
        return a == 1;
    }

    public static void f(String str, boolean z) {
        if (e()) {
            b bVar = new b(str, false, z);
            synchronized (d) {
                if (e()) {
                    e.add(bVar);
                }
            }
        }
    }

    public static void g() {
        if (b) {
            Object obj = d;
            synchronized (obj) {
                if (iv.e().g("trace-early-java-in-child")) {
                    if (a == 0) {
                        d();
                    }
                } else {
                    synchronized (obj) {
                        a = 0;
                        e = null;
                        f = null;
                    }
                }
            }
        }
    }

    @CalledByNative
    public static boolean getBackgroundStartupTracingFlag() {
        return c;
    }

    @CalledByNative
    public static void setBackgroundStartupTracingFlag(boolean z) {
        xz.a.a.edit().putBoolean("bg_startup_tracing", z).apply();
    }
}
