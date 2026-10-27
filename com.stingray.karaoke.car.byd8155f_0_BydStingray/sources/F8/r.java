package F8;

import g1.AbstractC1274a;
import q8.C2203g;

/* JADX INFO: loaded from: classes.dex */
public final class r extends AbstractC0282q implements InterfaceC0276k {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(A a10, A a11) {
        super(a10, a11);
        A7.m.f(a10, "lowerBound");
        A7.m.f(a11, "upperBound");
    }

    @Override // F8.InterfaceC0276k
    public final a0 A(AbstractC0287w abstractC0287w) {
        a0 a0VarJ;
        A7.m.f(abstractC0287w, "replacement");
        a0 a0VarK0 = abstractC0287w.K0();
        if (a0VarK0 instanceof AbstractC0282q) {
            a0VarJ = a0VarK0;
        } else {
            if (!(a0VarK0 instanceof A)) {
                throw new A9.k();
            }
            A a10 = (A) a0VarK0;
            a0VarJ = C0269d.j(a10, a10.L0(true));
        }
        return AbstractC0268c.g(a0VarJ, a0VarK0);
    }

    @Override // F8.AbstractC0287w
    public final AbstractC0287w I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A a10 = this.f2477m;
        A7.m.f(a10, "type");
        A a11 = this.f2478n;
        A7.m.f(a11, "type");
        return new r(a10, a11);
    }

    @Override // F8.a0
    public final a0 L0(boolean z6) {
        return C0269d.j(this.f2477m.L0(z6), this.f2478n.L0(z6));
    }

    @Override // F8.a0
    /* JADX INFO: renamed from: M0 */
    public final a0 I0(G8.f fVar) {
        A7.m.f(fVar, "kotlinTypeRefiner");
        A a10 = this.f2477m;
        A7.m.f(a10, "type");
        A a11 = this.f2478n;
        A7.m.f(a11, "type");
        return new r(a10, a11);
    }

    @Override // F8.a0
    public final a0 N0(H h10) {
        A7.m.f(h10, "newAttributes");
        return C0269d.j(this.f2477m.N0(h10), this.f2478n.N0(h10));
    }

    @Override // F8.AbstractC0282q
    public final A O0() {
        return this.f2477m;
    }

    @Override // F8.AbstractC0282q
    public final String P0(C2203g c2203g, C2203g c2203g2) {
        A7.m.f(c2203g, "renderer");
        boolean zN = c2203g2.f21281a.n();
        A a10 = this.f2478n;
        A a11 = this.f2477m;
        if (!zN) {
            return c2203g.F(c2203g.Y(a11), c2203g.Y(a10), AbstractC1274a.L(this));
        }
        return "(" + c2203g.Y(a11) + ".." + c2203g.Y(a10) + ')';
    }

    @Override // F8.InterfaceC0276k
    public final boolean W() {
        A a10 = this.f2477m;
        return (a10.B0().n() instanceof Q7.Q) && A7.m.a(a10.B0(), this.f2478n.B0());
    }

    @Override // F8.AbstractC0282q
    public final String toString() {
        return "(" + this.f2477m + ".." + this.f2478n + ')';
    }
}
