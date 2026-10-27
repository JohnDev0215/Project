package io.sentry;

import h2.AbstractC1347d;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class T1 implements InterfaceC1485m0 {
    public final io.sentry.protocol.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final V1 f15957m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final V1 f15958n;
    public transient B8.t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f15959p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f15960q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public W1 f15961r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f15962s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f15963t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f15964u;

    public T1(io.sentry.protocol.t tVar, V1 v12, String str, V1 v13, B8.t tVar2) {
        this(tVar, v12, v13, str, null, tVar2, null, "manual");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof T1)) {
            return false;
        }
        T1 t12 = (T1) obj;
        return this.l.equals(t12.l) && this.f15957m.equals(t12.f15957m) && h6.e.i(this.f15958n, t12.f15958n) && this.f15959p.equals(t12.f15959p) && h6.e.i(this.f15960q, t12.f15960q) && this.f15961r == t12.f15961r;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f15957m, this.f15958n, this.f15959p, this.f15960q, this.f15961r});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("trace_id");
        this.l.serialize(c1489n1, k10);
        c1489n1.w("span_id");
        this.f15957m.serialize(c1489n1, k10);
        V1 v12 = this.f15958n;
        if (v12 != null) {
            c1489n1.w("parent_span_id");
            v12.serialize(c1489n1, k10);
        }
        c1489n1.w("op");
        c1489n1.L(this.f15959p);
        if (this.f15960q != null) {
            c1489n1.w("description");
            c1489n1.L(this.f15960q);
        }
        if (this.f15961r != null) {
            c1489n1.w("status");
            c1489n1.I(k10, this.f15961r);
        }
        if (this.f15963t != null) {
            c1489n1.w("origin");
            c1489n1.I(k10, this.f15963t);
        }
        if (!this.f15962s.isEmpty()) {
            c1489n1.w("tags");
            c1489n1.I(k10, this.f15962s);
        }
        ConcurrentHashMap concurrentHashMap = this.f15964u;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f15964u, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }

    public T1(io.sentry.protocol.t tVar, V1 v12, V1 v13, String str, String str2, B8.t tVar2, W1 w1, String str3) {
        this.f15962s = new ConcurrentHashMap();
        this.f15963t = "manual";
        h6.e.q(tVar, "traceId is required");
        this.l = tVar;
        h6.e.q(v12, "spanId is required");
        this.f15957m = v12;
        h6.e.q(str, "operation is required");
        this.f15959p = str;
        this.f15958n = v13;
        this.o = tVar2;
        this.f15960q = str2;
        this.f15961r = w1;
        this.f15963t = str3;
    }

    public T1(T1 t12) {
        this.f15962s = new ConcurrentHashMap();
        this.f15963t = "manual";
        this.l = t12.l;
        this.f15957m = t12.f15957m;
        this.f15958n = t12.f15958n;
        this.o = t12.o;
        this.f15959p = t12.f15959p;
        this.f15960q = t12.f15960q;
        this.f15961r = t12.f15961r;
        ConcurrentHashMap concurrentHashMapP = h6.r.p(t12.f15962s);
        if (concurrentHashMapP != null) {
            this.f15962s = concurrentHashMapP;
        }
    }
}
