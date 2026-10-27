package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class E implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16656m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16657n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16658p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16659q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C1502g f16660r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f16661s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f16662t;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || E.class != obj.getClass()) {
            return false;
        }
        E e10 = (E) obj;
        return h6.e.i(this.l, e10.l) && h6.e.i(this.f16656m, e10.f16656m) && h6.e.i(this.f16657n, e10.f16657n) && h6.e.i(this.o, e10.o) && h6.e.i(this.f16658p, e10.f16658p);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16656m, this.f16657n, this.o, this.f16658p});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("email");
            c1489n1.L(this.l);
        }
        if (this.f16656m != null) {
            c1489n1.w("id");
            c1489n1.L(this.f16656m);
        }
        if (this.f16657n != null) {
            c1489n1.w("username");
            c1489n1.L(this.f16657n);
        }
        if (this.o != null) {
            c1489n1.w("segment");
            c1489n1.L(this.o);
        }
        if (this.f16658p != null) {
            c1489n1.w("ip_address");
            c1489n1.L(this.f16658p);
        }
        if (this.f16659q != null) {
            c1489n1.w("name");
            c1489n1.L(this.f16659q);
        }
        if (this.f16660r != null) {
            c1489n1.w("geo");
            this.f16660r.serialize(c1489n1, k10);
        }
        if (this.f16661s != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.f16661s);
        }
        ConcurrentHashMap concurrentHashMap = this.f16662t;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16662t, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
