package io.sentry.rrweb;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class j extends b implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16851n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f16852p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public HashMap f16853q;

    public j() {
        super(c.Meta);
        this.f16851n = "";
    }

    @Override // io.sentry.rrweb.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || j.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        j jVar = (j) obj;
        return this.o == jVar.o && this.f16852p == jVar.f16852p && h6.e.i(this.f16851n, jVar.f16851n);
    }

    @Override // io.sentry.rrweb.b
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.f16851n, Integer.valueOf(this.o), Integer.valueOf(this.f16852p)});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("type");
        c1489n1.I(k10, this.l);
        c1489n1.w("timestamp");
        c1489n1.H(this.f16836m);
        c1489n1.w("data");
        c1489n1.i();
        c1489n1.w("href");
        c1489n1.L(this.f16851n);
        c1489n1.w("height");
        c1489n1.H(this.o);
        c1489n1.w("width");
        c1489n1.H(this.f16852p);
        HashMap map = this.f16853q;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16853q, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        c1489n1.n();
    }
}
