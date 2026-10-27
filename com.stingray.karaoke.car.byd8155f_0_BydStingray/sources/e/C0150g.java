package E;

import G.InterfaceC0307q;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: E.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0150g implements InterfaceC0307q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f1352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1353b;

    public C0150g(D d, int i7) {
        this.f1352a = d;
        this.f1353b = i7;
    }

    @Override // G.InterfaceC0307q
    public final int b() {
        return this.f1352a.h().f1417m;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.util.List] */
    @Override // G.InterfaceC0307q
    public final int c() {
        return Math.min(b() - 1, ((u) AbstractC1998m.h0(this.f1352a.h().f1415j)).f1422a + this.f1353b);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.Collection] */
    @Override // G.InterfaceC0307q
    public final boolean d() {
        return !this.f1352a.h().f1415j.isEmpty();
    }

    @Override // G.InterfaceC0307q
    public final void e() {
        E0.F f10 = this.f1352a.f1310k;
        if (f10 != null) {
            f10.l();
        }
    }

    @Override // G.InterfaceC0307q
    public final int f() {
        return Math.max(0, this.f1352a.d.f1443b.i() - this.f1353b);
    }
}
