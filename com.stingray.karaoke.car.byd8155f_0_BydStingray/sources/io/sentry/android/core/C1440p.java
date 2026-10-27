package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.CallableC1526y;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.G0;
import io.sentry.H0;
import io.sentry.P1;
import io.sentry.U0;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import n7.AbstractC1985B;

/* JADX INFO: renamed from: io.sentry.android.core.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1440p implements io.sentry.W {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.K f16259m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f16260n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f16261p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final io.sentry.S f16262q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final B f16263r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.l f16266u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public H0 f16267v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f16269x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f16270y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Date f16271z;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f16264s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f16265t = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C1439o f16268w = null;

    public C1440p(Context context, B b10, io.sentry.android.core.internal.util.l lVar, io.sentry.K k10, String str, boolean z6, int i7, io.sentry.S s6) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        h6.e.q(k10, "ILogger is required");
        this.f16259m = k10;
        this.f16266u = lVar;
        h6.e.q(b10, "The BuildInfoProvider is required.");
        this.f16263r = b10;
        this.f16260n = str;
        this.o = z6;
        this.f16261p = i7;
        h6.e.q(s6, "The ISentryExecutorService is required.");
        this.f16262q = s6;
        this.f16271z = AbstractC1985B.f();
    }

    public final void a() {
        if (this.f16264s) {
            return;
        }
        this.f16264s = true;
        boolean z6 = this.o;
        io.sentry.K k10 = this.f16259m;
        if (!z6) {
            k10.k(EnumC1495p1.INFO, "Profiling is disabled in options.", new Object[0]);
            return;
        }
        String str = this.f16260n;
        if (str == null) {
            k10.k(EnumC1495p1.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
            return;
        }
        int i7 = this.f16261p;
        if (i7 <= 0) {
            k10.k(EnumC1495p1.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(i7));
        } else {
            this.f16268w = new C1439o(str, ((int) TimeUnit.SECONDS.toMicros(1L)) / i7, this.f16266u, this.f16262q, this.f16259m, this.f16263r);
        }
    }

    public final boolean b() {
        A4.m mVar;
        String string;
        C1439o c1439o = this.f16268w;
        if (c1439o == null) {
            return false;
        }
        synchronized (c1439o) {
            int i7 = c1439o.c;
            mVar = null;
            if (i7 == 0) {
                c1439o.f16258n.k(EnumC1495p1.WARNING, "Disabling profiling because intervaUs is set to %d", Integer.valueOf(i7));
            } else if (c1439o.o) {
                c1439o.f16258n.k(EnumC1495p1.WARNING, "Profiling has already started...", new Object[0]);
            } else {
                c1439o.l.getClass();
                c1439o.f16250e = new File(c1439o.f16249b, UUID.randomUUID() + ".trace");
                c1439o.f16256k.clear();
                c1439o.f16253h.clear();
                c1439o.f16254i.clear();
                c1439o.f16255j.clear();
                io.sentry.android.core.internal.util.l lVar = c1439o.f16252g;
                C1437m c1437m = new C1437m(c1439o);
                if (lVar.f16235r) {
                    string = UUID.randomUUID().toString();
                    lVar.f16234q.put(string, c1437m);
                    lVar.c();
                } else {
                    string = null;
                }
                c1439o.f16251f = string;
                try {
                    c1439o.d = c1439o.f16257m.r(new A2.l(c1439o, 17), 30000L);
                } catch (RejectedExecutionException e10) {
                    c1439o.f16258n.s(EnumC1495p1.ERROR, "Failed to call the executor. Profiling will not be automatically finished. Did you call Sentry.close()?", e10);
                }
                c1439o.f16248a = SystemClock.elapsedRealtimeNanos();
                Date dateF = AbstractC1985B.f();
                long elapsedCpuTime = Process.getElapsedCpuTime();
                try {
                    Debug.startMethodTracingSampling(c1439o.f16250e.getPath(), 3000000, c1439o.c);
                    c1439o.o = true;
                    long j3 = c1439o.f16248a;
                    A4.m mVar2 = new A4.m();
                    mVar2.f426a = j3;
                    mVar2.f427b = elapsedCpuTime;
                    mVar2.c = dateF;
                    mVar = mVar2;
                } catch (Throwable th) {
                    c1439o.a(null, false);
                    c1439o.f16258n.s(EnumC1495p1.ERROR, "Unable to start a profile: ", th);
                    c1439o.o = false;
                }
            }
        }
        if (mVar == null) {
            return false;
        }
        this.f16269x = mVar.f426a;
        this.f16270y = mVar.f427b;
        this.f16271z = (Date) mVar.c;
        return true;
    }

    public final synchronized G0 c(String str, String str2, String str3, boolean z6, List list, F1 f12) {
        try {
            ActivityManager.MemoryInfo memoryInfo = null;
            if (this.f16268w == null) {
                return null;
            }
            this.f16263r.getClass();
            H0 h02 = this.f16267v;
            if (h02 != null && h02.l.equals(str2)) {
                int i7 = this.f16265t;
                if (i7 > 0) {
                    this.f16265t = i7 - 1;
                }
                this.f16259m.k(EnumC1495p1.DEBUG, "Transaction %s (%s) finished.", str, str3);
                if (this.f16265t != 0) {
                    H0 h03 = this.f16267v;
                    if (h03 != null) {
                        h03.a(Long.valueOf(SystemClock.elapsedRealtimeNanos()), Long.valueOf(this.f16269x), Long.valueOf(Process.getElapsedCpuTime()), Long.valueOf(this.f16270y));
                    }
                    return null;
                }
                C1438n c1438nA = this.f16268w.a(list, false);
                if (c1438nA == null) {
                    return null;
                }
                long j3 = c1438nA.f16245m - this.f16269x;
                ArrayList arrayList = new ArrayList(1);
                H0 h04 = this.f16267v;
                if (h04 != null) {
                    arrayList.add(h04);
                }
                this.f16267v = null;
                this.f16265t = 0;
                io.sentry.K k10 = this.f16259m;
                try {
                    ActivityManager activityManager = (ActivityManager) this.l.getSystemService("activity");
                    ActivityManager.MemoryInfo memoryInfo2 = new ActivityManager.MemoryInfo();
                    if (activityManager != null) {
                        activityManager.getMemoryInfo(memoryInfo2);
                        memoryInfo = memoryInfo2;
                    } else {
                        k10.k(EnumC1495p1.INFO, "Error getting MemoryInfo.", new Object[0]);
                    }
                } catch (Throwable th) {
                    k10.s(EnumC1495p1.ERROR, "Error getting MemoryInfo.", th);
                }
                String string = memoryInfo != null ? Long.toString(memoryInfo.totalMem) : "0";
                String[] strArr = Build.SUPPORTED_ABIS;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((H0) it.next()).a(Long.valueOf(c1438nA.f16245m), Long.valueOf(this.f16269x), Long.valueOf(c1438nA.f16246n), Long.valueOf(this.f16270y));
                    c1438nA = c1438nA;
                }
                C1438n c1438n = c1438nA;
                File file = (File) c1438n.o;
                Date date = this.f16271z;
                String string2 = Long.toString(j3);
                this.f16263r.getClass();
                int i10 = Build.VERSION.SDK_INT;
                String str4 = (strArr == null || strArr.length <= 0) ? "" : strArr[0];
                CallableC1526y callableC1526y = new CallableC1526y(3);
                this.f16263r.getClass();
                String str5 = Build.MANUFACTURER;
                this.f16263r.getClass();
                String str6 = Build.MODEL;
                this.f16263r.getClass();
                return new G0(file, date, arrayList, str, str2, str3, string2, i10, str4, callableC1526y, str5, str6, Build.VERSION.RELEASE, this.f16263r.a(), string, f12.getProguardUuid(), f12.getRelease(), f12.getEnvironment(), (c1438n.l || z6) ? "timeout" : "normal", (HashMap) c1438n.f16247p);
            }
            this.f16259m.k(EnumC1495p1.INFO, "Transaction %s (%s) finished, but was not currently being profiled. Skipping", str, str3);
            return null;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // io.sentry.W
    public final void close() {
        H0 h02 = this.f16267v;
        if (h02 != null) {
            c(h02.f15865n, h02.l, h02.f15864m, true, null, U0.b().q());
        } else {
            int i7 = this.f16265t;
            if (i7 != 0) {
                this.f16265t = i7 - 1;
            }
        }
        C1439o c1439o = this.f16268w;
        if (c1439o != null) {
            synchronized (c1439o) {
                try {
                    Future future = c1439o.d;
                    if (future != null) {
                        future.cancel(true);
                        c1439o.d = null;
                    }
                    if (c1439o.o) {
                        c1439o.a(null, true);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // io.sentry.W
    public final synchronized void d(P1 p12) {
        if (this.f16265t > 0 && this.f16267v == null) {
            this.f16267v = new H0(p12, Long.valueOf(this.f16269x), Long.valueOf(this.f16270y));
        }
    }

    @Override // io.sentry.W
    public final synchronized G0 e(P1 p12, List list, F1 f12) {
        return c(p12.f15912e, p12.f15910a.toString(), p12.f15911b.c.l.toString(), false, list, f12);
    }

    @Override // io.sentry.W
    public final boolean isRunning() {
        return this.f16265t != 0;
    }

    @Override // io.sentry.W
    public final synchronized void start() {
        try {
            this.f16263r.getClass();
            a();
            int i7 = this.f16265t + 1;
            this.f16265t = i7;
            if (i7 == 1 && b()) {
                this.f16259m.k(EnumC1495p1.DEBUG, "Profiler started.", new Object[0]);
            } else {
                this.f16265t--;
                this.f16259m.k(EnumC1495p1.WARNING, "A profile is already running. This profile will be ignored.", new Object[0]);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
