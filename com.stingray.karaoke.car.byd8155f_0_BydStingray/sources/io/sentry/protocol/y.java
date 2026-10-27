package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class y implements InterfaceC1485m0 {
    public List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ConcurrentHashMap f16806m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Boolean f16807n;
    public ConcurrentHashMap o;

    public y(List list) {
        this.l = list;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("frames");
            c1489n1.I(k10, this.l);
        }
        if (this.f16806m != null) {
            c1489n1.w("registers");
            c1489n1.I(k10, this.f16806m);
        }
        if (this.f16807n != null) {
            c1489n1.w("snapshot");
            c1489n1.J(this.f16807n);
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
