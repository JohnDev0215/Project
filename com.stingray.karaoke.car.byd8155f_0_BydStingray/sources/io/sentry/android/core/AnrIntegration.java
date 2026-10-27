package io.sentry.android.core;

import android.content.Context;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class AnrIntegration implements io.sentry.Z, Closeable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static C1425a f16031p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Object f16032q = new Object();
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16033m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f16034n = new Object();
    public F1 o;

    public AnrIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
    }

    public final void b(SentryAndroidOptions sentryAndroidOptions) {
        synchronized (f16032q) {
            try {
                if (f16031p == null) {
                    io.sentry.K logger = sentryAndroidOptions.getLogger();
                    EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
                    logger.k(enumC1495p1, "ANR timeout in milliseconds: %d", Long.valueOf(sentryAndroidOptions.getAnrTimeoutIntervalMillis()));
                    C1425a c1425a = new C1425a(sentryAndroidOptions.getAnrTimeoutIntervalMillis(), sentryAndroidOptions.isAnrReportInDebug(), new U(this, sentryAndroidOptions), sentryAndroidOptions.getLogger(), this.l);
                    f16031p = c1425a;
                    c1425a.start();
                    sentryAndroidOptions.getLogger().k(enumC1495p1, "AnrIntegration installed.", new Object[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f16034n) {
            this.f16033m = true;
        }
        synchronized (f16032q) {
            try {
                C1425a c1425a = f16031p;
                if (c1425a != null) {
                    c1425a.interrupt();
                    f16031p = null;
                    F1 f12 = this.o;
                    if (f12 != null) {
                        f12.getLogger().k(EnumC1495p1.DEBUG, "AnrIntegration removed.", new Object[0]);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.o = f12;
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) f12;
        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "AnrIntegration enabled: %s", Boolean.valueOf(sentryAndroidOptions.isAnrEnabled()));
        if (sentryAndroidOptions.isAnrEnabled()) {
            n7.z.c("Anr");
            try {
                sentryAndroidOptions.getExecutorService().submit(new r(this, 0, sentryAndroidOptions));
            } catch (Throwable th) {
                sentryAndroidOptions.getLogger().s(EnumC1495p1.DEBUG, "Failed to start AnrIntegration on executor thread.", th);
            }
        }
    }
}
