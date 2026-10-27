package io.sentry.android.core;

import android.annotation.SuppressLint;
import android.content.Context;
import b7.RunnableC1004e;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class AnrV2Integration implements io.sentry.Z, Closeable {
    public static final long o = TimeUnit.DAYS.toMillis(91);
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.transport.d f16035m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SentryAndroidOptions f16036n;

    public AnrV2Integration(Context context) {
        io.sentry.transport.d dVar = io.sentry.transport.d.f16897a;
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        this.f16035m = dVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        SentryAndroidOptions sentryAndroidOptions = this.f16036n;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "AnrV2Integration removed.", new Object[0]);
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16036n = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "AnrIntegration enabled: %s", Boolean.valueOf(this.f16036n.isAnrEnabled()));
        if (this.f16036n.getCacheDirPath() == null) {
            this.f16036n.getLogger().k(EnumC1495p1.INFO, "Cache dir is not set, unable to process ANRs", new Object[0]);
            return;
        }
        if (this.f16036n.isAnrEnabled()) {
            try {
                f12.getExecutorService().submit(new RunnableC1004e(this.l, this.f16036n, this.f16035m));
            } catch (Throwable th) {
                f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to start AnrProcessor.", th);
            }
            f12.getLogger().k(EnumC1495p1.DEBUG, "AnrV2Integration installed.", new Object[0]);
            n7.z.c("AnrV2");
        }
    }
}
