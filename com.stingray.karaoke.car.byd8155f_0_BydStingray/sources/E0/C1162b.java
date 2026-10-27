package e0;

import F8.C0285u;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1162b extends C1163c {
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.util.List] */
    public C1162b(int i7, C1171k c1171k) {
        InterfaceC2782k iVar;
        synchronized (AbstractC1174n.f13721b) {
            ?? r12 = AbstractC1174n.f13725h;
            iVar = (InterfaceC2782k) AbstractC1998m.t0(r12);
            iVar = iVar == null ? new E.i(r12, 1) : iVar;
        }
        super(i7, c1171k, null, iVar);
    }

    @Override // e0.C1163c
    public final C1163c B(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        return (C1163c) ((AbstractC1167g) AbstractC1174n.f(new C1173m(0, new C1161a(interfaceC2782k, interfaceC2782k2, 0))));
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final void c() {
        synchronized (AbstractC1174n.f13721b) {
            int i7 = this.d;
            if (i7 >= 0) {
                AbstractC1174n.u(i7);
                this.d = -1;
            }
        }
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
        AbstractC1174n.a();
    }

    @Override // e0.C1163c, e0.AbstractC1167g
    public final AbstractC1167g t(InterfaceC2782k interfaceC2782k) {
        return (AbstractC1167g) AbstractC1174n.f(new C1173m(0, new C0285u(2, interfaceC2782k)));
    }

    @Override // e0.C1163c
    public final AbstractC1179s v() {
        throw new IllegalStateException("Cannot apply the global snapshot directly. Call Snapshot.advanceGlobalSnapshot");
    }
}
