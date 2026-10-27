package io.sentry.clientreport;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e implements InterfaceC1485m0 {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16501m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Long f16502n;
    public HashMap o;

    public e(String str, String str2, Long l) {
        this.l = str;
        this.f16501m = str2;
        this.f16502n = l;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("reason");
        c1489n1.L(this.l);
        c1489n1.w("category");
        c1489n1.L(this.f16501m);
        c1489n1.w("quantity");
        c1489n1.K(this.f16502n);
        HashMap map = this.o;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.o, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }

    public final String toString() {
        return "DiscardedEvent{reason='" + this.l + "', category='" + this.f16501m + "', quantity=" + this.f16502n + '}';
    }
}
