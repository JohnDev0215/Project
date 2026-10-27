package E0;

import u.C2481u;

/* JADX INFO: renamed from: E0.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0164n extends g0.n {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f1689y = e0.f(this);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public g0.n f1690z;

    @Override // g0.n
    public final void C0() {
        super.C0();
        for (g0.n nVar = this.f1690z; nVar != null; nVar = nVar.f14688q) {
            nVar.C0();
        }
    }

    @Override // g0.n
    public final void D0() {
        for (g0.n nVar = this.f1690z; nVar != null; nVar = nVar.f14688q) {
            nVar.D0();
        }
        super.D0();
    }

    @Override // g0.n
    public final void E0() {
        super.E0();
        for (g0.n nVar = this.f1690z; nVar != null; nVar = nVar.f14688q) {
            nVar.E0();
        }
    }

    @Override // g0.n
    public final void F0(g0.n nVar) {
        this.l = nVar;
        for (g0.n nVar2 = this.f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
            nVar2.F0(nVar);
        }
    }

    @Override // g0.n
    public final void G0(d0 d0Var) {
        this.f14690s = d0Var;
        for (g0.n nVar = this.f1690z; nVar != null; nVar = nVar.f14688q) {
            nVar.G0(d0Var);
        }
    }

    public final void H0(InterfaceC0163m interfaceC0163m) {
        g0.n nVar = ((g0.n) interfaceC0163m).l;
        if (nVar != interfaceC0163m) {
            g0.n nVar2 = interfaceC0163m instanceof g0.n ? (g0.n) interfaceC0163m : null;
            g0.n nVar3 = nVar2 != null ? nVar2.f14687p : null;
            if (nVar != this.l || !A7.m.a(nVar3, this)) {
                throw new IllegalStateException("Cannot delegate to an already delegated node");
            }
            return;
        }
        if (nVar.f14695x) {
            l9.d.h0("Cannot delegate to an already attached node");
            throw null;
        }
        nVar.F0(this.l);
        int i7 = this.f14686n;
        int iG = e0.g(nVar);
        nVar.f14686n = iG;
        int i10 = this.f14686n;
        int i11 = iG & 2;
        if (i11 != 0 && (i10 & 2) != 0 && !(this instanceof InterfaceC0174y)) {
            l9.d.h0("Delegating to multiple LayoutModifierNodes without the delegating node implementing LayoutModifierNode itself is not allowed.\nDelegating Node: " + this + "\nDelegate Node: " + nVar);
            throw null;
        }
        nVar.f14688q = this.f1690z;
        this.f1690z = nVar;
        nVar.f14687p = this;
        J0(iG | i10, false);
        if (this.f14695x) {
            if (i11 == 0 || (i7 & 2) != 0) {
                G0(this.f14690s);
            } else {
                Y y10 = AbstractC0156f.t(this).f1469H;
                this.l.G0(null);
                y10.k();
            }
            nVar.x0();
            nVar.D0();
            e0.a(nVar);
        }
    }

    public final void I0(InterfaceC0163m interfaceC0163m) {
        g0.n nVar = null;
        for (g0.n nVar2 = this.f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
            if (nVar2 == interfaceC0163m) {
                boolean z6 = nVar2.f14695x;
                if (z6) {
                    C2481u c2481u = e0.f1665a;
                    if (!z6) {
                        l9.d.h0("autoInvalidateRemovedNode called on unattached node");
                        throw null;
                    }
                    e0.b(nVar2, -1, 2);
                    nVar2.E0();
                    nVar2.y0();
                }
                nVar2.F0(nVar2);
                nVar2.o = 0;
                if (nVar == null) {
                    this.f1690z = nVar2.f14688q;
                } else {
                    nVar.f14688q = nVar2.f14688q;
                }
                nVar2.f14688q = null;
                nVar2.f14687p = null;
                int i7 = this.f14686n;
                int iG = e0.g(this);
                J0(iG, true);
                if (this.f14695x && (i7 & 2) != 0 && (iG & 2) == 0) {
                    Y y10 = AbstractC0156f.t(this).f1469H;
                    this.l.G0(null);
                    y10.k();
                    return;
                }
                return;
            }
            nVar = nVar2;
        }
        throw new IllegalStateException(("Could not find delegate: " + interfaceC0163m).toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v2, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public final void J0(int i7, boolean z6) {
        g0.n nVar;
        int i10 = this.f14686n;
        this.f14686n = i7;
        if (i10 != i7) {
            g0.n nVar2 = this.l;
            if (nVar2 == this) {
                this.o = i7;
            }
            boolean z10 = this.f14695x;
            ?? r22 = this;
            if (z10) {
                while (r22 != 0) {
                    i7 |= r22.f14686n;
                    r22.f14686n = i7;
                    if (r22 == nVar2) {
                        break;
                    } else {
                        r22 = r22.f14687p;
                    }
                }
                if (z6 && r22 == nVar2) {
                    i7 = e0.g(nVar2);
                    nVar2.f14686n = i7;
                }
                int i11 = i7 | ((r22 == 0 || (nVar = r22.f14688q) == null) ? 0 : nVar.o);
                for (?? r23 = r22; r23 != 0; r23 = r23.f14687p) {
                    i11 |= r23.f14686n;
                    r23.o = i11;
                }
            }
        }
    }

    @Override // g0.n
    public final void x0() {
        super.x0();
        for (g0.n nVar = this.f1690z; nVar != null; nVar = nVar.f14688q) {
            nVar.G0(this.f14690s);
            if (!nVar.f14695x) {
                nVar.x0();
            }
        }
    }

    @Override // g0.n
    public final void y0() {
        for (g0.n nVar = this.f1690z; nVar != null; nVar = nVar.f14688q) {
            nVar.y0();
        }
        super.y0();
    }
}
