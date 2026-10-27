package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class n implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16749m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16750n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16751p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f16752q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ConcurrentHashMap f16753r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Long f16754s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f16755t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f16756u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f16757v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ConcurrentHashMap f16758w;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || n.class != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        return h6.e.i(this.l, nVar.l) && h6.e.i(this.f16749m, nVar.f16749m) && h6.e.i(this.f16750n, nVar.f16750n) && h6.e.i(this.f16751p, nVar.f16751p) && h6.e.i(this.f16752q, nVar.f16752q) && h6.e.i(this.f16753r, nVar.f16753r) && h6.e.i(this.f16754s, nVar.f16754s) && h6.e.i(this.f16756u, nVar.f16756u) && h6.e.i(this.f16757v, nVar.f16757v);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16749m, this.f16750n, this.f16751p, this.f16752q, this.f16753r, this.f16754s, this.f16756u, this.f16757v});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("url");
            c1489n1.L(this.l);
        }
        if (this.f16749m != null) {
            c1489n1.w("method");
            c1489n1.L(this.f16749m);
        }
        if (this.f16750n != null) {
            c1489n1.w("query_string");
            c1489n1.L(this.f16750n);
        }
        if (this.o != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.o);
        }
        if (this.f16751p != null) {
            c1489n1.w("cookies");
            c1489n1.L(this.f16751p);
        }
        if (this.f16752q != null) {
            c1489n1.w("headers");
            c1489n1.I(k10, this.f16752q);
        }
        if (this.f16753r != null) {
            c1489n1.w("env");
            c1489n1.I(k10, this.f16753r);
        }
        if (this.f16755t != null) {
            c1489n1.w("other");
            c1489n1.I(k10, this.f16755t);
        }
        if (this.f16756u != null) {
            c1489n1.w("fragment");
            c1489n1.I(k10, this.f16756u);
        }
        if (this.f16754s != null) {
            c1489n1.w("body_size");
            c1489n1.I(k10, this.f16754s);
        }
        if (this.f16757v != null) {
            c1489n1.w("api_target");
            c1489n1.I(k10, this.f16757v);
        }
        ConcurrentHashMap concurrentHashMap = this.f16758w;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16758w, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
