package io.sentry;

import h2.AbstractC1347d;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class H0 implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f15864m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f15865n;
    public Long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Long f15866p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Long f15867q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Long f15868r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f15869s;

    public H0(V v10, Long l, Long l10) {
        this.l = v10.h().toString();
        this.f15864m = v10.p().l.toString();
        this.f15865n = v10.getName().isEmpty() ? "unknown" : v10.getName();
        this.o = l;
        this.f15867q = l10;
    }

    public final void a(Long l, Long l10, Long l11, Long l12) {
        if (this.f15866p == null) {
            this.f15866p = Long.valueOf(l.longValue() - l10.longValue());
            this.o = Long.valueOf(this.o.longValue() - l10.longValue());
            this.f15868r = Long.valueOf(l11.longValue() - l12.longValue());
            this.f15867q = Long.valueOf(this.f15867q.longValue() - l12.longValue());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || H0.class != obj.getClass()) {
            return false;
        }
        H0 h02 = (H0) obj;
        return this.l.equals(h02.l) && this.f15864m.equals(h02.f15864m) && this.f15865n.equals(h02.f15865n) && this.o.equals(h02.o) && this.f15867q.equals(h02.f15867q) && h6.e.i(this.f15868r, h02.f15868r) && h6.e.i(this.f15866p, h02.f15866p) && h6.e.i(this.f15869s, h02.f15869s);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f15864m, this.f15865n, this.o, this.f15866p, this.f15867q, this.f15868r, this.f15869s});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("id");
        c1489n1.I(k10, this.l);
        c1489n1.w("trace_id");
        c1489n1.I(k10, this.f15864m);
        c1489n1.w("name");
        c1489n1.I(k10, this.f15865n);
        c1489n1.w("relative_start_ns");
        c1489n1.I(k10, this.o);
        c1489n1.w("relative_end_ns");
        c1489n1.I(k10, this.f15866p);
        c1489n1.w("relative_cpu_start_ms");
        c1489n1.I(k10, this.f15867q);
        c1489n1.w("relative_cpu_end_ms");
        c1489n1.I(k10, this.f15868r);
        ConcurrentHashMap concurrentHashMap = this.f15869s;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f15869s, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
