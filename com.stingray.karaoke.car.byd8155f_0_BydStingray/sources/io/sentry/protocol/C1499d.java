package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: io.sentry.protocol.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1499d implements InterfaceC1485m0 {
    public q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f16687m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f16688n;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("sdk_info");
            c1489n1.I(k10, this.l);
        }
        if (this.f16687m != null) {
            c1489n1.w("images");
            c1489n1.I(k10, this.f16687m);
        }
        HashMap map = this.f16688n;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16688n, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
