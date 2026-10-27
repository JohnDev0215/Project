package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.protocol.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1497b implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16685m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ConcurrentHashMap f16686n;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1497b.class != obj.getClass()) {
            return false;
        }
        C1497b c1497b = (C1497b) obj;
        return h6.e.i(this.l, c1497b.l) && h6.e.i(this.f16685m, c1497b.f16685m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16685m});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("name");
            c1489n1.L(this.l);
        }
        if (this.f16685m != null) {
            c1489n1.w("version");
            c1489n1.L(this.f16685m);
        }
        ConcurrentHashMap concurrentHashMap = this.f16686n;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16686n, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
