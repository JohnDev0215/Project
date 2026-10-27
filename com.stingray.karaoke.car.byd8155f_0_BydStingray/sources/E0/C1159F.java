package e0;

import T.C0735d;
import u.C2453A;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1159F extends C1163c {
    public final C1163c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f13681p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f13682q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public InterfaceC2782k f13683r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public InterfaceC2782k f13684s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final long f13685t;

    public C1159F(C1163c c1163c, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, boolean z6, boolean z10) {
        InterfaceC2782k interfaceC2782kI;
        InterfaceC2782k interfaceC2782kX;
        super(0, C1171k.f13713p, AbstractC1174n.l(interfaceC2782k, (c1163c == null || (interfaceC2782kX = c1163c.f()) == null) ? ((C1162b) AbstractC1174n.f13726i.get()).f13693e : interfaceC2782kX, z6), AbstractC1174n.b(interfaceC2782k2, (c1163c == null || (interfaceC2782kI = c1163c.i()) == null) ? ((C1162b) AbstractC1174n.f13726i.get()).f13694f : interfaceC2782kI));
        this.o = c1163c;
        this.f13681p = z6;
        this.f13682q = z10;
        this.f13683r = this.f13693e;
        this.f13684s = this.f13694f;
        this.f13685t = C0735d.C();
    }

    @Override // e0.C1163c
    public final void A(C2453A c2453a) {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.C1163c
    public final C1163c B(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        InterfaceC2782k interfaceC2782kL = AbstractC1174n.l(interfaceC2782k, this.f13683r, true);
        InterfaceC2782k interfaceC2782kB = AbstractC1174n.b(interfaceC2782k2, this.f13684s);
        return !this.f13681p ? new C1159F(C().B(null, interfaceC2782kB), interfaceC2782kL, interfaceC2782kB, false, true) : C().B(interfaceC2782kL, interfaceC2782kB);
    }

    public final C1163c C() {
        C1163c c1163c = this.o;
        return c1163c == null ? (C1163c) AbstractC1174n.f13726i.get() : c1163c;
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void c() {
        C1163c c1163c;
        this.c = true;
        if (!this.f13682q || (c1163c = this.o) == null) {
            return;
        }
        c1163c.c();
    }

    @Override // e0.AbstractC1167g
    public final int d() {
        return C().d();
    }

    @Override // e0.AbstractC1167g
    public final C1171k e() {
        return C().e();
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final InterfaceC2782k f() {
        return this.f13683r;
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final boolean g() {
        return C().g();
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final int h() {
        return C().h();
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final InterfaceC2782k i() {
        return this.f13684s;
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void k() {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void l() {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void m() {
        C().m();
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void n(InterfaceC1154A interfaceC1154A) {
        C().n(interfaceC1154A);
    }

    @Override // e0.AbstractC1167g
    public final void q(int i7) {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.AbstractC1167g
    public final void r(C1171k c1171k) {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void s(int i7) {
        C().s(i7);
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final AbstractC1167g t(InterfaceC2782k interfaceC2782k) {
        InterfaceC2782k interfaceC2782kL = AbstractC1174n.l(interfaceC2782k, this.f13683r, true);
        return !this.f13681p ? AbstractC1174n.h(C().t(null), interfaceC2782kL, true) : C().t(interfaceC2782kL);
    }

    @Override // e0.C1163c
    public final AbstractC1179s v() {
        return C().v();
    }

    @Override // e0.C1163c
    public final C2453A w() {
        return C().w();
    }

    @Override // e0.C1163c
    /* JADX INFO: renamed from: x */
    public final InterfaceC2782k f() {
        return this.f13683r;
    }
}
