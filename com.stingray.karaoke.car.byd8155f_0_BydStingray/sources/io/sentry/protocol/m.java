package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class m implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16745n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16746p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f16747q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ConcurrentHashMap f16748r;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return h6.e.i(this.l, mVar.l) && h6.e.i(this.f16744m, mVar.f16744m) && h6.e.i(this.f16745n, mVar.f16745n) && h6.e.i(this.o, mVar.o) && h6.e.i(this.f16746p, mVar.f16746p) && h6.e.i(this.f16747q, mVar.f16747q);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16744m, this.f16745n, this.o, this.f16746p, this.f16747q});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("name");
            c1489n1.L(this.l);
        }
        if (this.f16744m != null) {
            c1489n1.w("version");
            c1489n1.L(this.f16744m);
        }
        if (this.f16745n != null) {
            c1489n1.w("raw_description");
            c1489n1.L(this.f16745n);
        }
        if (this.o != null) {
            c1489n1.w("build");
            c1489n1.L(this.o);
        }
        if (this.f16746p != null) {
            c1489n1.w("kernel_version");
            c1489n1.L(this.f16746p);
        }
        if (this.f16747q != null) {
            c1489n1.w("rooted");
            c1489n1.J(this.f16747q);
        }
        ConcurrentHashMap concurrentHashMap = this.f16748r;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16748r, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
