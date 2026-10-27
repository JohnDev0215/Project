package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i implements InterfaceC1485m0 {
    public final Number l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16731m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ConcurrentHashMap f16732n;

    public i(Number number, String str) {
        this.l = number;
        this.f16731m = str;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("value");
        c1489n1.K(this.l);
        String str = this.f16731m;
        if (str != null) {
            c1489n1.w("unit");
            c1489n1.L(str);
        }
        ConcurrentHashMap concurrentHashMap = this.f16732n;
        if (concurrentHashMap != null) {
            for (String str2 : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16732n, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
    }
}
