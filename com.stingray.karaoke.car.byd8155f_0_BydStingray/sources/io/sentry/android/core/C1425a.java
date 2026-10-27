package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Handler;
import android.os.SystemClock;
import h3.C1350a;
import h8.AbstractC1387d;
import io.sentry.C1480k1;
import io.sentry.EnumC1495p1;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: io.sentry.android.core.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1425a extends Thread {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final U f16131m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final B f16132n;
    public final C1350a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f16133p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f16134q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.sentry.K f16135r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile long f16136s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final AtomicBoolean f16137t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Context f16138u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final A2.l f16139v;

    public C1425a(long j3, boolean z6, U u4, io.sentry.K k10, Context context) {
        C1350a c1350a = new C1350a(10);
        B b10 = new B();
        super("|ANR-WatchDog|");
        this.f16136s = 0L;
        this.f16137t = new AtomicBoolean(false);
        this.o = c1350a;
        this.f16134q = j3;
        this.f16133p = 500L;
        this.l = z6;
        this.f16131m = u4;
        this.f16135r = k10;
        this.f16132n = b10;
        this.f16138u = context;
        this.f16139v = new A2.l(this, c1350a);
        if (j3 < 1000) {
            throw new IllegalArgumentException(String.format("ANRWatchDog: timeoutIntervalMillis has to be at least %d ms", 1000L));
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        List<ActivityManager.ProcessErrorStateInfo> processesInErrorState;
        this.f16139v.run();
        while (!isInterrupted()) {
            ((Handler) this.f16132n.f16042a).post(this.f16139v);
            try {
                Thread.sleep(this.f16133p);
                this.o.getClass();
                if (SystemClock.uptimeMillis() - this.f16136s > this.f16134q) {
                    if (this.l || !(Debug.isDebuggerConnected() || Debug.waitingForDebugger())) {
                        ActivityManager activityManager = (ActivityManager) this.f16138u.getSystemService("activity");
                        if (activityManager != null) {
                            try {
                                processesInErrorState = activityManager.getProcessesInErrorState();
                            } catch (Throwable th) {
                                this.f16135r.s(EnumC1495p1.ERROR, "Error getting ActivityManager#getProcessesInErrorState.", th);
                                processesInErrorState = null;
                            }
                            if (processesInErrorState != null) {
                                Iterator<ActivityManager.ProcessErrorStateInfo> it = processesInErrorState.iterator();
                                while (it.hasNext()) {
                                    if (it.next().condition == 2) {
                                    }
                                }
                            }
                        }
                        if (this.f16137t.compareAndSet(false, true)) {
                            ApplicationNotResponding applicationNotResponding = new ApplicationNotResponding(android.hardware.bydauto.ac.tsimpl.a.f(this.f16134q, " ms.", new StringBuilder("Application Not Responding for at least ")), ((Handler) this.f16132n.f16042a).getLooper().getThread());
                            U u4 = this.f16131m;
                            ((AnrIntegration) u4.f16117a).getClass();
                            SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) u4.f16118b;
                            sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "ANR triggered with message: %s", applicationNotResponding.getMessage());
                            boolean zEquals = Boolean.TRUE.equals(A.f16013b.f16014a);
                            String strG = "ANR for at least " + sentryAndroidOptions.getAnrTimeoutIntervalMillis() + " ms.";
                            if (zEquals) {
                                strG = M5.b.g("Background ", strG);
                            }
                            ApplicationNotResponding applicationNotResponding2 = new ApplicationNotResponding(strG, applicationNotResponding.l);
                            io.sentry.protocol.j jVar = new io.sentry.protocol.j();
                            jVar.l = "ANR";
                            C1480k1 c1480k1 = new C1480k1(new io.sentry.exception.a(jVar, applicationNotResponding2, applicationNotResponding2.l, true));
                            c1480k1.f16601F = EnumC1495p1.ERROR;
                            io.sentry.E.f15834a.s(c1480k1, AbstractC1387d.c(new C1442s(zEquals)));
                        }
                    } else {
                        this.f16135r.k(EnumC1495p1.DEBUG, "An ANR was detected but ignored because the debugger is connected.", new Object[0]);
                        this.f16137t.set(true);
                    }
                }
            } catch (InterruptedException e10) {
                try {
                    Thread.currentThread().interrupt();
                    this.f16135r.k(EnumC1495p1.WARNING, "Interrupted: %s", e10.getMessage());
                    return;
                } catch (SecurityException unused) {
                    this.f16135r.k(EnumC1495p1.WARNING, "Failed to interrupt due to SecurityException: %s", e10.getMessage());
                    return;
                }
            }
        }
    }
}
