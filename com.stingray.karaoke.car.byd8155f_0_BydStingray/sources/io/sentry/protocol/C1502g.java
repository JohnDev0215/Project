package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.protocol.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1502g implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16721m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16722n;
    public ConcurrentHashMap o;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("city");
            c1489n1.L(this.l);
        }
        if (this.f16721m != null) {
            c1489n1.w("country_code");
            c1489n1.L(this.f16721m);
        }
        if (this.f16722n != null) {
            c1489n1.w("region");
            c1489n1.L(this.f16722n);
        }
        ConcurrentHashMap concurrentHashMap = this.o;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.o, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
