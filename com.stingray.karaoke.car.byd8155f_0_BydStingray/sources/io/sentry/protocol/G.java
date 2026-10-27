package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class G implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16665m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16666n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Double f16667p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Double f16668q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Double f16669r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Double f16670s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f16671t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Double f16672u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f16673v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public HashMap f16674w;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("rendering_system");
            c1489n1.L(this.l);
        }
        if (this.f16665m != null) {
            c1489n1.w("type");
            c1489n1.L(this.f16665m);
        }
        if (this.f16666n != null) {
            c1489n1.w("identifier");
            c1489n1.L(this.f16666n);
        }
        if (this.o != null) {
            c1489n1.w("tag");
            c1489n1.L(this.o);
        }
        if (this.f16667p != null) {
            c1489n1.w("width");
            c1489n1.K(this.f16667p);
        }
        if (this.f16668q != null) {
            c1489n1.w("height");
            c1489n1.K(this.f16668q);
        }
        if (this.f16669r != null) {
            c1489n1.w("x");
            c1489n1.K(this.f16669r);
        }
        if (this.f16670s != null) {
            c1489n1.w("y");
            c1489n1.K(this.f16670s);
        }
        if (this.f16671t != null) {
            c1489n1.w("visibility");
            c1489n1.L(this.f16671t);
        }
        if (this.f16672u != null) {
            c1489n1.w("alpha");
            c1489n1.K(this.f16672u);
        }
        List list = this.f16673v;
        if (list != null && !list.isEmpty()) {
            c1489n1.w("children");
            c1489n1.I(k10, this.f16673v);
        }
        HashMap map = this.f16674w;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16674w, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
