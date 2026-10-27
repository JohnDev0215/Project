package io.sentry.profilemeasurements;

import h2.AbstractC1347d;
import h6.e;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC1485m0 {
    public ConcurrentHashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16645m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f16646n;

    public b(Long l, Number number) {
        this.f16645m = l.toString();
        this.f16646n = number.doubleValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return e.i(this.l, bVar.l) && this.f16645m.equals(bVar.f16645m) && this.f16646n == bVar.f16646n;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16645m, Double.valueOf(this.f16646n)});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("value");
        c1489n1.I(k10, Double.valueOf(this.f16646n));
        c1489n1.w("elapsed_since_start_ns");
        c1489n1.I(k10, this.f16645m);
        ConcurrentHashMap concurrentHashMap = this.l;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.l, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
