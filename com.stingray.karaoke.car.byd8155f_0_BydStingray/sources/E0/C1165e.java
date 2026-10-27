package e0;

import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1165e extends AbstractC1167g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2782k f13702e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC1167g f13703f;

    public C1165e(int i7, C1171k c1171k, InterfaceC2782k interfaceC2782k, AbstractC1167g abstractC1167g) {
        super(i7, c1171k);
        this.f13702e = interfaceC2782k;
        this.f13703f = abstractC1167g;
        abstractC1167g.k();
    }

    @Override // e0.AbstractC1167g
    public final void c() {
        if (this.c) {
            return;
        }
        int i7 = this.f13707b;
        AbstractC1167g abstractC1167g = this.f13703f;
        if (i7 != abstractC1167g.d()) {
            a();
        }
        abstractC1167g.l();
        this.c = true;
        synchronized (AbstractC1174n.f13721b) {
            int i10 = this.d;
            if (i10 >= 0) {
                AbstractC1174n.u(i10);
                this.d = -1;
            }
        }
    }

    @Override // e0.AbstractC1167g
    public final InterfaceC2782k f() {
        return this.f13702e;
    }

    @Override // e0.AbstractC1167g
    public final boolean g() {
        return true;
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
    }

    @Override // e0.AbstractC1167g
    public final void n(InterfaceC1154A interfaceC1154A) {
        P p10 = AbstractC1174n.f13720a;
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot");
    }

    @Override // e0.AbstractC1167g
    public final AbstractC1167g t(InterfaceC2782k interfaceC2782k) {
        return new C1165e(this.f13707b, this.f13706a, AbstractC1174n.l(interfaceC2782k, this.f13702e, true), this.f13703f);
    }
}
