package F;

import E0.F;
import G.InterfaceC0307q;
import n7.AbstractC1998m;

/* JADX INFO: loaded from: classes.dex */
public final class d implements InterfaceC0307q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f1867a;

    public d(y yVar) {
        this.f1867a = yVar;
    }

    @Override // G.InterfaceC0307q
    public final int b() {
        return this.f1867a.g().f1923j;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.List] */
    @Override // G.InterfaceC0307q
    public final int c() {
        return ((p) AbstractC1998m.h0(this.f1867a.g().f1920g)).f1927a;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.Collection] */
    @Override // G.InterfaceC0307q
    public final boolean d() {
        return !this.f1867a.g().f1920g.isEmpty();
    }

    @Override // G.InterfaceC0307q
    public final void e() {
        F f10 = this.f1867a.f1978h;
        if (f10 != null) {
            f10.l();
        }
    }

    @Override // G.InterfaceC0307q
    public final int f() {
        return this.f1867a.f1974b.f1443b.i();
    }
}
