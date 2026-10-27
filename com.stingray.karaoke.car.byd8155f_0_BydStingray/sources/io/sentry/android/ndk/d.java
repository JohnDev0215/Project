package io.sentry.android.ndk;

import io.sentry.C1457d;
import io.sentry.EnumC1495p1;
import io.sentry.P0;
import io.sentry.android.core.SentryAndroidOptions;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class d extends P0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SentryAndroidOptions f16312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f16313b;

    public d(SentryAndroidOptions sentryAndroidOptions) {
        NativeScope nativeScope = new NativeScope();
        h6.e.q(sentryAndroidOptions, "The SentryOptions object is required.");
        this.f16312a = sentryAndroidOptions;
        this.f16313b = nativeScope;
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void b(final String str, final String str2) {
        SentryAndroidOptions sentryAndroidOptions = this.f16312a;
        try {
            sentryAndroidOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.ndk.c
                @Override // java.lang.Runnable
                public final void run() {
                    ((NativeScope) this.l.f16313b).getClass();
                    NativeScope.nativeSetTag(str, str2);
                }
            });
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().p(EnumC1495p1.ERROR, th, "Scope sync setTag(%s) has an error.", str);
        }
    }

    @Override // io.sentry.P0, io.sentry.Q
    public final void e(final C1457d c1457d) {
        SentryAndroidOptions sentryAndroidOptions = this.f16312a;
        try {
            sentryAndroidOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.ndk.b
                @Override // java.lang.Runnable
                public final void run() {
                    d dVar = this.l;
                    SentryAndroidOptions sentryAndroidOptions2 = dVar.f16312a;
                    C1457d c1457d2 = c1457d;
                    EnumC1495p1 enumC1495p1 = c1457d2.f16517s;
                    String strB = null;
                    String lowerCase = enumC1495p1 != null ? enumC1495p1.name().toLowerCase(Locale.ROOT) : null;
                    String strM = AbstractC1985B.m(c1457d2.a());
                    try {
                        ConcurrentHashMap concurrentHashMap = c1457d2.f16514p;
                        if (!concurrentHashMap.isEmpty()) {
                            strB = sentryAndroidOptions2.getSerializer().b(concurrentHashMap);
                        }
                    } catch (Throwable th) {
                        sentryAndroidOptions2.getLogger().p(EnumC1495p1.ERROR, th, "Breadcrumb data is not serializable.", new Object[0]);
                    }
                    String str = strB;
                    String str2 = c1457d2.f16513n;
                    String str3 = c1457d2.f16515q;
                    String str4 = c1457d2.o;
                    ((NativeScope) dVar.f16313b).getClass();
                    NativeScope.nativeAddBreadcrumb(lowerCase, str2, str3, str4, strM, str);
                }
            });
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().p(EnumC1495p1.ERROR, th, "Scope sync addBreadcrumb has an error.", new Object[0]);
        }
    }
}
