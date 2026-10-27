package io.sentry.android.core;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Bundle;
import h3.C1350a;
import io.sentry.C1489n1;
import io.sentry.C1524x0;
import io.sentry.EnumC1495p1;
import io.sentry.U0;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class SentryInitProvider extends G {
    @Override // android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        if (SentryInitProvider.class.getName().equals(providerInfo.authority)) {
            throw new IllegalStateException("An applicationId is required to fulfill the manifest placeholder.");
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        Bundle bundleP;
        io.sentry.android.core.performance.e.d(this);
        P p10 = new P();
        Context context = getContext();
        if (context == null) {
            p10.k(EnumC1495p1.FATAL, "App. Context from ContentProvider is null", new Object[0]);
            io.sentry.android.core.performance.e.e(this);
            return false;
        }
        try {
            bundleP = h6.m.p(context, p10, null);
        } catch (Throwable th) {
            p10.s(EnumC1495p1.ERROR, "Failed to read auto-init from android manifest metadata.", th);
        }
        boolean zY = bundleP != null ? h6.m.y(bundleP, p10, "io.sentry.auto-init", true) : true;
        if (zY) {
            int i7 = V.f16122b;
            C1350a c1350a = new C1350a(10);
            synchronized (V.class) {
                try {
                    try {
                        try {
                            U0.c(new C1524x0(6), new U(p10, context, c1350a));
                            io.sentry.J jB = U0.b();
                            if (h6.i.t()) {
                                if (jB.q().isEnableAutoSessionTracking()) {
                                    AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                                    jB.l(new G2.a(atomicBoolean, 11));
                                    if (!atomicBoolean.get()) {
                                        jB.p();
                                    }
                                }
                                jB.q().getReplayController().start();
                            }
                        } catch (IllegalAccessException e10) {
                            p10.s(EnumC1495p1.FATAL, "Fatal error during SentryAndroid.init(...)", e10);
                            throw new RuntimeException("Failed to initialize Sentry's SDK", e10);
                        } catch (InvocationTargetException e11) {
                            p10.s(EnumC1495p1.FATAL, "Fatal error during SentryAndroid.init(...)", e11);
                            throw new RuntimeException("Failed to initialize Sentry's SDK", e11);
                        }
                    } catch (InstantiationException e12) {
                        p10.s(EnumC1495p1.FATAL, "Fatal error during SentryAndroid.init(...)", e12);
                        throw new RuntimeException("Failed to initialize Sentry's SDK", e12);
                    } catch (NoSuchMethodException e13) {
                        p10.s(EnumC1495p1.FATAL, "Fatal error during SentryAndroid.init(...)", e13);
                        throw new RuntimeException("Failed to initialize Sentry's SDK", e13);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            C1489n1.s().f("AutoInit");
        }
        io.sentry.android.core.performance.e.e(this);
        return true;
    }

    @Override // android.content.ContentProvider
    public final void shutdown() {
        U0.a();
    }
}
