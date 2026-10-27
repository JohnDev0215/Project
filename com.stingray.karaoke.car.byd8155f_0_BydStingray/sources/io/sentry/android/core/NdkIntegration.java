package io.sentry.android.core;

import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class NdkIntegration implements io.sentry.Z, Closeable {
    public final Class l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16077m;

    public NdkIntegration(Class cls) {
        this.l = cls;
    }

    public static void b(SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.setEnableNdk(false);
        sentryAndroidOptions.setEnableScopeSync(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [io.sentry.android.core.SentryAndroidOptions] */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        SentryAndroidOptions sentryAndroidOptions = this.f16077m;
        if (sentryAndroidOptions == null || !sentryAndroidOptions.isEnableNdk()) {
            return;
        }
        Class cls = this.l;
        try {
            if (cls != null) {
                try {
                    try {
                        cls.getMethod("close", null).invoke(null, null);
                        this.f16077m.getLogger().k(EnumC1495p1.DEBUG, "NdkIntegration removed.", new Object[0]);
                    } catch (NoSuchMethodException e10) {
                        this.f16077m.getLogger().s(EnumC1495p1.ERROR, "Failed to invoke the SentryNdk.close method.", e10);
                    }
                } finally {
                }
                this = this.f16077m;
                b(this);
            }
        } catch (Throwable th) {
            b(this.f16077m);
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        Class cls;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16077m = sentryAndroidOptions;
        boolean zIsEnableNdk = sentryAndroidOptions.isEnableNdk();
        io.sentry.K logger = this.f16077m.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "NdkIntegration enabled: %s", Boolean.valueOf(zIsEnableNdk));
        if (!zIsEnableNdk || (cls = this.l) == null) {
            b(this.f16077m);
            return;
        }
        if (this.f16077m.getCacheDirPath() == null) {
            this.f16077m.getLogger().k(EnumC1495p1.ERROR, "No cache dir path is defined in options.", new Object[0]);
            b(this.f16077m);
            return;
        }
        try {
            cls.getMethod("init", SentryAndroidOptions.class).invoke(null, this.f16077m);
            this.f16077m.getLogger().k(enumC1495p1, "NdkIntegration installed.", new Object[0]);
            n7.z.c("Ndk");
        } catch (NoSuchMethodException e10) {
            b(this.f16077m);
            this.f16077m.getLogger().s(EnumC1495p1.ERROR, "Failed to invoke the SentryNdk.init method.", e10);
        } catch (Throwable th) {
            b(this.f16077m);
            this.f16077m.getLogger().s(EnumC1495p1.ERROR, "Failed to initialize SentryNdk.", th);
        }
    }
}
