package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16769m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16770n;
    public Long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public y f16771p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public j f16772q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public HashMap f16773r;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("type");
            c1489n1.L(this.l);
        }
        if (this.f16769m != null) {
            c1489n1.w("value");
            c1489n1.L(this.f16769m);
        }
        if (this.f16770n != null) {
            c1489n1.w("module");
            c1489n1.L(this.f16770n);
        }
        if (this.o != null) {
            c1489n1.w("thread_id");
            c1489n1.K(this.o);
        }
        if (this.f16771p != null) {
            c1489n1.w("stacktrace");
            c1489n1.I(k10, this.f16771p);
        }
        if (this.f16772q != null) {
            c1489n1.w("mechanism");
            c1489n1.I(k10, this.f16772q);
        }
        HashMap map = this.f16773r;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16773r, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
