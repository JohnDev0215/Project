package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.protocol.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1496a implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Date f16675m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16676n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16677p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16678q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f16679r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractMap f16680s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f16681t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f16682u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Boolean f16683v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ConcurrentHashMap f16684w;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1496a.class != obj.getClass()) {
            return false;
        }
        C1496a c1496a = (C1496a) obj;
        return h6.e.i(this.l, c1496a.l) && h6.e.i(this.f16675m, c1496a.f16675m) && h6.e.i(this.f16676n, c1496a.f16676n) && h6.e.i(this.o, c1496a.o) && h6.e.i(this.f16677p, c1496a.f16677p) && h6.e.i(this.f16678q, c1496a.f16678q) && h6.e.i(this.f16679r, c1496a.f16679r) && h6.e.i(this.f16680s, c1496a.f16680s) && h6.e.i(this.f16683v, c1496a.f16683v) && h6.e.i(this.f16681t, c1496a.f16681t) && h6.e.i(this.f16682u, c1496a.f16682u);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16675m, this.f16676n, this.o, this.f16677p, this.f16678q, this.f16679r, this.f16680s, this.f16683v, this.f16681t, this.f16682u});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("app_identifier");
            c1489n1.L(this.l);
        }
        if (this.f16675m != null) {
            c1489n1.w("app_start_time");
            c1489n1.I(k10, this.f16675m);
        }
        if (this.f16676n != null) {
            c1489n1.w("device_app_hash");
            c1489n1.L(this.f16676n);
        }
        if (this.o != null) {
            c1489n1.w("build_type");
            c1489n1.L(this.o);
        }
        if (this.f16677p != null) {
            c1489n1.w("app_name");
            c1489n1.L(this.f16677p);
        }
        if (this.f16678q != null) {
            c1489n1.w("app_version");
            c1489n1.L(this.f16678q);
        }
        if (this.f16679r != null) {
            c1489n1.w("app_build");
            c1489n1.L(this.f16679r);
        }
        AbstractMap abstractMap = this.f16680s;
        if (abstractMap != null && !abstractMap.isEmpty()) {
            c1489n1.w("permissions");
            c1489n1.I(k10, this.f16680s);
        }
        if (this.f16683v != null) {
            c1489n1.w("in_foreground");
            c1489n1.J(this.f16683v);
        }
        if (this.f16681t != null) {
            c1489n1.w("view_names");
            c1489n1.I(k10, this.f16681t);
        }
        if (this.f16682u != null) {
            c1489n1.w("start_type");
            c1489n1.L(this.f16682u);
        }
        ConcurrentHashMap concurrentHashMap = this.f16684w;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16684w, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
