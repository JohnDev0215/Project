package io.sentry.android.core;

import android.os.Debug;
import io.sentry.C1506r0;
import io.sentry.E0;

/* JADX INFO: renamed from: io.sentry.android.core.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1435k implements io.sentry.P {
    @Override // io.sentry.P
    public final void a(E0 e02) {
        e02.f15835a = new C1506r0(System.currentTimeMillis(), Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), Debug.getNativeHeapSize() - Debug.getNativeHeapFreeSize());
    }

    @Override // io.sentry.P
    public final void b() {
    }
}
