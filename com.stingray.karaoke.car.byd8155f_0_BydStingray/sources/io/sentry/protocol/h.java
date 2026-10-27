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
public final class h implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f16723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16724n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Integer f16725p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16726q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Boolean f16727r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f16728s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f16729t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f16730u;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        return h6.e.i(this.l, hVar.l) && h6.e.i(this.f16723m, hVar.f16723m) && h6.e.i(this.f16724n, hVar.f16724n) && h6.e.i(this.o, hVar.o) && h6.e.i(this.f16725p, hVar.f16725p) && h6.e.i(this.f16726q, hVar.f16726q) && h6.e.i(this.f16727r, hVar.f16727r) && h6.e.i(this.f16728s, hVar.f16728s) && h6.e.i(this.f16729t, hVar.f16729t);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16723m, this.f16724n, this.o, this.f16725p, this.f16726q, this.f16727r, this.f16728s, this.f16729t});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("name");
            c1489n1.L(this.l);
        }
        if (this.f16723m != null) {
            c1489n1.w("id");
            c1489n1.K(this.f16723m);
        }
        if (this.f16724n != null) {
            c1489n1.w("vendor_id");
            c1489n1.L(this.f16724n);
        }
        if (this.o != null) {
            c1489n1.w("vendor_name");
            c1489n1.L(this.o);
        }
        if (this.f16725p != null) {
            c1489n1.w("memory_size");
            c1489n1.K(this.f16725p);
        }
        if (this.f16726q != null) {
            c1489n1.w("api_type");
            c1489n1.L(this.f16726q);
        }
        if (this.f16727r != null) {
            c1489n1.w("multi_threaded_rendering");
            c1489n1.J(this.f16727r);
        }
        if (this.f16728s != null) {
            c1489n1.w("version");
            c1489n1.L(this.f16728s);
        }
        if (this.f16729t != null) {
            c1489n1.w("npot_support");
            c1489n1.L(this.f16729t);
        }
        ConcurrentHashMap concurrentHashMap = this.f16730u;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16730u, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
