package io.sentry.android.core;

import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.C1466g;
import io.sentry.C1506r0;
import io.sentry.E0;
import io.sentry.EnumC1495p1;
import java.io.File;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: io.sentry.android.core.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1439o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final File f16249b;
    public final int c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f16251f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.l f16252g;
    public final B l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.S f16257m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.K f16258n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f16248a = 0;
    public Future d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public File f16250e = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayDeque f16253h = new ArrayDeque();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayDeque f16254i = new ArrayDeque();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayDeque f16255j = new ArrayDeque();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final HashMap f16256k = new HashMap();
    public boolean o = false;

    public C1439o(String str, int i7, io.sentry.android.core.internal.util.l lVar, io.sentry.S s6, io.sentry.K k10, B b10) {
        h6.e.q(str, "TracesFilesDirPath is required");
        this.f16249b = new File(str);
        this.c = i7;
        h6.e.q(k10, "Logger is required");
        this.f16258n = k10;
        h6.e.q(s6, "ExecutorService is required.");
        this.f16257m = s6;
        h6.e.q(lVar, "SentryFrameMetricsCollector is required");
        this.f16252g = lVar;
        h6.e.q(b10, "The BuildInfoProvider is required.");
        this.l = b10;
    }

    public final synchronized C1438n a(List list, boolean z6) {
        if (!this.o) {
            this.f16258n.k(EnumC1495p1.WARNING, "Profiler not running", new Object[0]);
            return null;
        }
        this.l.getClass();
        try {
            Debug.stopMethodTracing();
        } finally {
            try {
            } finally {
            }
        }
        this.f16252g.a(this.f16251f);
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        long elapsedCpuTime = Process.getElapsedCpuTime();
        if (this.f16250e == null) {
            this.f16258n.k(EnumC1495p1.ERROR, "Trace file does not exists", new Object[0]);
            return null;
        }
        if (!this.f16254i.isEmpty()) {
            this.f16256k.put("slow_frame_renders", new io.sentry.profilemeasurements.a("nanosecond", this.f16254i));
        }
        if (!this.f16255j.isEmpty()) {
            this.f16256k.put("frozen_frame_renders", new io.sentry.profilemeasurements.a("nanosecond", this.f16255j));
        }
        if (!this.f16253h.isEmpty()) {
            this.f16256k.put("screen_frame_rates", new io.sentry.profilemeasurements.a("hz", this.f16253h));
        }
        b(list);
        Future future = this.d;
        if (future != null) {
            future.cancel(true);
            this.d = null;
        }
        return new C1438n(jElapsedRealtimeNanos, elapsedCpuTime, z6, this.f16250e, this.f16256k);
    }

    public final void b(List list) {
        this.l.getClass();
        long jElapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - this.f16248a) - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        if (list != null) {
            ArrayDeque arrayDeque = new ArrayDeque(list.size());
            ArrayDeque arrayDeque2 = new ArrayDeque(list.size());
            ArrayDeque arrayDeque3 = new ArrayDeque(list.size());
            synchronized (list) {
                try {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        E0 e02 = (E0) it.next();
                        C1466g c1466g = e02.f15836b;
                        C1506r0 c1506r0 = e02.f15835a;
                        if (c1466g != null) {
                            arrayDeque3.add(new io.sentry.profilemeasurements.b(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(c1466g.f16539a) + jElapsedRealtimeNanos), Double.valueOf(c1466g.f16540b)));
                        }
                        if (c1506r0 != null && c1506r0.f16826b > -1) {
                            arrayDeque.add(new io.sentry.profilemeasurements.b(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(c1506r0.f16825a) + jElapsedRealtimeNanos), Long.valueOf(c1506r0.f16826b)));
                        }
                        if (c1506r0 != null && c1506r0.c > -1) {
                            arrayDeque2.add(new io.sentry.profilemeasurements.b(Long.valueOf(TimeUnit.MILLISECONDS.toNanos(c1506r0.f16825a) + jElapsedRealtimeNanos), Long.valueOf(c1506r0.c)));
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (!arrayDeque3.isEmpty()) {
                this.f16256k.put("cpu_usage", new io.sentry.profilemeasurements.a("percent", arrayDeque3));
            }
            if (!arrayDeque.isEmpty()) {
                this.f16256k.put("memory_footprint", new io.sentry.profilemeasurements.a("byte", arrayDeque));
            }
            if (arrayDeque2.isEmpty()) {
                return;
            }
            this.f16256k.put("memory_native_footprint", new io.sentry.profilemeasurements.a("byte", arrayDeque2));
        }
    }
}
