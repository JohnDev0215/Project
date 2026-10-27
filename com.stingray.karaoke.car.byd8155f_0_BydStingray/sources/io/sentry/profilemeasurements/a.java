package io.sentry.profilemeasurements;

import h2.AbstractC1347d;
import h6.e;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a implements InterfaceC1485m0 {
    public ConcurrentHashMap l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16643m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Collection f16644n;

    public a(String str, AbstractCollection abstractCollection) {
        this.f16643m = str;
        this.f16644n = abstractCollection;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return e.i(this.l, aVar.l) && this.f16643m.equals(aVar.f16643m) && new ArrayList(this.f16644n).equals(new ArrayList(aVar.f16644n));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16643m, this.f16644n});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("unit");
        c1489n1.I(k10, this.f16643m);
        c1489n1.w("values");
        c1489n1.I(k10, this.f16644n);
        ConcurrentHashMap concurrentHashMap = this.l;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.l, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
