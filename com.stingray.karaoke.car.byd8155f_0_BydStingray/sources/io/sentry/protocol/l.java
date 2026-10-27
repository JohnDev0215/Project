package io.sentry.protocol;

import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l implements InterfaceC1485m0 {
    public double l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public double f16741m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f16742n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f16743p;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("min");
        c1489n1.G(this.l);
        c1489n1.w("max");
        c1489n1.G(this.f16741m);
        c1489n1.w("sum");
        c1489n1.G(this.f16742n);
        c1489n1.w("count");
        c1489n1.H(this.o);
        if (this.f16743p != null) {
            c1489n1.w("tags");
            c1489n1.I(k10, this.f16743p);
        }
        c1489n1.n();
    }
}
