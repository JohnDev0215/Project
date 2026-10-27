package e0;

import T.C0735d;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1160G extends AbstractC1167g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC1167g f13686e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f13687f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public InterfaceC2782k f13688g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f13689h;

    public C1160G(AbstractC1167g abstractC1167g, InterfaceC2782k interfaceC2782k, boolean z6) {
        InterfaceC2782k interfaceC2782kF;
        super(0, C1171k.f13713p);
        this.f13686e = abstractC1167g;
        this.f13687f = z6;
        this.f13688g = AbstractC1174n.l(interfaceC2782k, (abstractC1167g == null || (interfaceC2782kF = abstractC1167g.f()) == null) ? ((C1162b) AbstractC1174n.f13726i.get()).f13693e : interfaceC2782kF, false);
        this.f13689h = C0735d.C();
    }

    @Override // e0.AbstractC1167g
    public final void c() {
        AbstractC1167g abstractC1167g;
        this.c = true;
        if (!this.f13687f || (abstractC1167g = this.f13686e) == null) {
            return;
        }
        abstractC1167g.c();
    }

    @Override // e0.AbstractC1167g
    public final int d() {
        return u().d();
    }

    @Override // e0.AbstractC1167g
    public final C1171k e() {
        return u().e();
    }

    @Override // e0.AbstractC1167g
    public final InterfaceC2782k f() {
        return this.f13688g;
    }

    @Override // e0.AbstractC1167g
    public final boolean g() {
        return u().g();
    }

    @Override // e0.AbstractC1167g
    public final InterfaceC2782k i() {
        return null;
    }

    @Override // e0.AbstractC1167g
    public final void k() {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.AbstractC1167g
    public final void l() {
        AbstractC1179s.g();
        throw null;
    }

    @Override // e0.AbstractC1167g
    public final void m() {
        u().m();
    }

    @Override // e0.AbstractC1167g
    public final void n(InterfaceC1154A interfaceC1154A) {
        u().n(interfaceC1154A);
    }

    @Override // e0.AbstractC1167g
    public final AbstractC1167g t(InterfaceC2782k interfaceC2782k) {
        return AbstractC1174n.h(u().t(null), AbstractC1174n.l(interfaceC2782k, this.f13688g, true), true);
    }

    public final AbstractC1167g u() {
        AbstractC1167g abstractC1167g = this.f13686e;
        return abstractC1167g == null ? (AbstractC1167g) AbstractC1174n.f13726i.get() : abstractC1167g;
    }
}
