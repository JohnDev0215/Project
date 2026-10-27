package io.sentry.android.core;

import android.os.SystemClock;
import io.sentry.F1;
import io.sentry.android.fragment.FragmentLifecycleIntegration;
import io.sentry.android.timber.SentryTimberIntegration;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f16121a = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f16122b = 0;

    public static void a(F1 f12, boolean z6, boolean z10) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (io.sentry.Z z11 : f12.getIntegrations()) {
            if (z6 && (z11 instanceof FragmentLifecycleIntegration)) {
                arrayList2.add(z11);
            }
            if (z10 && (z11 instanceof SentryTimberIntegration)) {
                arrayList.add(z11);
            }
        }
        if (arrayList2.size() > 1) {
            for (int i7 = 0; i7 < arrayList2.size() - 1; i7++) {
                f12.getIntegrations().remove((io.sentry.Z) arrayList2.get(i7));
            }
        }
        if (arrayList.size() > 1) {
            for (int i10 = 0; i10 < arrayList.size() - 1; i10++) {
                f12.getIntegrations().remove((io.sentry.Z) arrayList.get(i10));
            }
        }
    }
}
