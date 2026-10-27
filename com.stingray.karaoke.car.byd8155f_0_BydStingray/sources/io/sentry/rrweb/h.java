package io.sentry.rrweb;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class h implements InterfaceC1485m0 {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f16845m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f16846n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f16847p;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("id");
        c1489n1.H(this.l);
        c1489n1.w("x");
        c1489n1.G(this.f16845m);
        c1489n1.w("y");
        c1489n1.G(this.f16846n);
        c1489n1.w("timeOffset");
        c1489n1.H(this.o);
        HashMap map = this.f16847p;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16847p, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
