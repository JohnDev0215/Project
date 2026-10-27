package io.sentry;

import h2.AbstractC1347d;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1505q1 implements InterfaceC1485m0 {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16819m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16820n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Long f16821p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f16822q;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1505q1.class != obj.getClass()) {
            return false;
        }
        return h6.e.i(this.f16819m, ((C1505q1) obj).f16819m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16819m});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("type");
        c1489n1.H(this.l);
        if (this.f16819m != null) {
            c1489n1.w("address");
            c1489n1.L(this.f16819m);
        }
        if (this.f16820n != null) {
            c1489n1.w("package_name");
            c1489n1.L(this.f16820n);
        }
        if (this.o != null) {
            c1489n1.w("class_name");
            c1489n1.L(this.o);
        }
        if (this.f16821p != null) {
            c1489n1.w("thread_id");
            c1489n1.K(this.f16821p);
        }
        ConcurrentHashMap concurrentHashMap = this.f16822q;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16822q, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
