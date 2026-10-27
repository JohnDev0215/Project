package e0;

import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1166f extends AbstractC1167g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2782k f13704e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f13705f;

    public C1166f(int i7, C1171k c1171k, InterfaceC2782k interfaceC2782k) {
        super(i7, c1171k);
        this.f13704e = interfaceC2782k;
        this.f13705f = 1;
    }

    @Override // e0.AbstractC1167g
    public final void c() {
        if (this.c) {
            return;
        }
        l();
        this.c = true;
        synchronized (AbstractC1174n.f13721b) {
            int i7 = this.d;
            if (i7 >= 0) {
                AbstractC1174n.u(i7);
                this.d = -1;
            }
        }
    }

    @Override // e0.AbstractC1167g
    public final InterfaceC2782k f() {
        return this.f13704e;
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
        this.f13705f++;
    }

    @Override // e0.AbstractC1167g
    public final void l() {
        int i7 = this.f13705f - 1;
        this.f13705f = i7;
        if (i7 == 0) {
            a();
        }
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
        AbstractC1174n.d(this);
        return new C1165e(this.f13707b, this.f13706a, AbstractC1174n.l(interfaceC2782k, this.f13704e, true), this);
    }
}
