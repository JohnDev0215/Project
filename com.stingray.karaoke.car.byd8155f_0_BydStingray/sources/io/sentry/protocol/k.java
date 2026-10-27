package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16739m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f16740n;
    public ConcurrentHashMap o;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("formatted");
            c1489n1.L(this.l);
        }
        if (this.f16739m != null) {
            c1489n1.w("message");
            c1489n1.L(this.f16739m);
        }
        List list = this.f16740n;
        if (list != null && !list.isEmpty()) {
            c1489n1.w("params");
            c1489n1.I(k10, this.f16740n);
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
