package io.sentry.android.core;

import io.sentry.EnumC1495p1;
import io.sentry.N0;
import io.sentry.O0;

/* JADX INFO: renamed from: io.sentry.android.core.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1430f implements O0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ActivityLifecycleIntegration f16165m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ io.sentry.V f16166n;

    public /* synthetic */ C1430f(ActivityLifecycleIntegration activityLifecycleIntegration, io.sentry.V v10, int i7) {
        this.l = i7;
        this.f16165m = activityLifecycleIntegration;
        this.f16166n = v10;
    }

    @Override // io.sentry.O0
    public final void i(N0 n02) {
        switch (this.l) {
            case 0:
                ActivityLifecycleIntegration activityLifecycleIntegration = this.f16165m;
                io.sentry.V v10 = this.f16166n;
                activityLifecycleIntegration.getClass();
                synchronized (n02.f15902m) {
                    if (n02.f15893a == v10) {
                        n02.a();
                    }
                    break;
                }
                return;
            default:
                ActivityLifecycleIntegration activityLifecycleIntegration2 = this.f16165m;
                io.sentry.V v11 = this.f16166n;
                activityLifecycleIntegration2.getClass();
                synchronized (n02.f15902m) {
                    if (n02.f15893a == null) {
                        n02.e(v11);
                    } else {
                        SentryAndroidOptions sentryAndroidOptions = activityLifecycleIntegration2.o;
                        if (sentryAndroidOptions != null) {
                            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "Transaction '%s' won't be bound to the Scope since there's one already in there.", v11.getName());
                        }
                    }
                    break;
                }
                return;
        }
    }
}
