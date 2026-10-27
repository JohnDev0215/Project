package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class v implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16777m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16778n;
    public ConcurrentHashMap o;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("name");
            c1489n1.L(this.l);
        }
        if (this.f16777m != null) {
            c1489n1.w("version");
            c1489n1.L(this.f16777m);
        }
        if (this.f16778n != null) {
            c1489n1.w("raw_description");
            c1489n1.L(this.f16778n);
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
