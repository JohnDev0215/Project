package io.sentry.android.core;

import android.content.Context;
import android.telephony.TelephonyManager;
import h8.AbstractC1386c;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class PhoneStateBreadcrumbsIntegration implements io.sentry.Z, Closeable {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16087m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public S f16088n;
    public TelephonyManager o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16089p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f16090q = new Object();

    public PhoneStateBreadcrumbsIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
    }

    public final void b(F1 f12) {
        TelephonyManager telephonyManager = (TelephonyManager) this.l.getSystemService("phone");
        this.o = telephonyManager;
        if (telephonyManager == null) {
            f12.getLogger().k(EnumC1495p1.INFO, "TelephonyManager is not available", new Object[0]);
            return;
        }
        try {
            S s6 = new S();
            this.f16088n = s6;
            this.o.listen(s6, 32);
            f12.getLogger().k(EnumC1495p1.DEBUG, "PhoneStateBreadcrumbsIntegration installed.", new Object[0]);
            n7.z.c("PhoneStateBreadcrumbs");
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.INFO, th, "TelephonyManager is not available or ready to use.", new Object[0]);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        S s6;
        synchronized (this.f16090q) {
            this.f16089p = true;
        }
        TelephonyManager telephonyManager = this.o;
        if (telephonyManager == null || (s6 = this.f16088n) == null) {
            return;
        }
        telephonyManager.listen(s6, 0);
        this.f16088n = null;
        SentryAndroidOptions sentryAndroidOptions = this.f16087m;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "PhoneStateBreadcrumbsIntegration removed.", new Object[0]);
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16087m = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "enableSystemEventBreadcrumbs enabled: %s", Boolean.valueOf(this.f16087m.isEnableSystemEventBreadcrumbs()));
        if (this.f16087m.isEnableSystemEventBreadcrumbs() && AbstractC1386c.l(this.l, "android.permission.READ_PHONE_STATE")) {
            try {
                f12.getExecutorService().submit(new r(this, 1, f12));
            } catch (Throwable th) {
                f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to start PhoneStateBreadcrumbsIntegration on executor thread.", th);
            }
        }
    }
}
