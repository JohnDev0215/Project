package g.a.d.b.a0.a;

import g.a.d.b.e;
import g.a.d.b.h;
import g.a.d.d.g;

/* JADX INFO: loaded from: classes.dex */
public class d extends h.c {
    public d(g.a.d.b.d dVar, e eVar, e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public d(g.a.d.b.d dVar, e eVar, e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public d(g.a.d.b.d dVar, e eVar, e eVar2, e[] eVarArr, boolean z) {
        super(dVar, eVar, eVar2, eVarArr);
        this.f5510f = z;
    }

    @Override // g.a.d.b.h
    public h H() {
        return (u() || this.f5508d.i()) ? this : N(false).a(this);
    }

    @Override // g.a.d.b.h
    public h J() {
        if (u()) {
            return this;
        }
        return this.f5508d.i() ? i().v() : N(true);
    }

    @Override // g.a.d.b.h
    public h K(h hVar) {
        return this == hVar ? H() : u() ? hVar : hVar.u() ? J() : this.f5508d.i() ? hVar : N(false).a(hVar);
    }

    public c L(c cVar, int[] iArr) {
        c cVar2 = (c) i().o();
        if (cVar.h()) {
            return cVar2;
        }
        c cVar3 = new c();
        if (iArr == null) {
            iArr = cVar3.f5281i;
            b.j(cVar.f5281i, iArr);
        }
        b.j(iArr, cVar3.f5281i);
        int[] iArr2 = cVar3.f5281i;
        b.e(iArr2, cVar2.f5281i, iArr2);
        return cVar3;
    }

    public c M() {
        e[] eVarArr = this.f5509e;
        c cVar = (c) eVarArr[1];
        if (cVar != null) {
            return cVar;
        }
        c cVarL = L((c) eVarArr[0], null);
        eVarArr[1] = cVarL;
        return cVarL;
    }

    public d N(boolean z) {
        c cVar = (c) this.f5507c;
        c cVar2 = (c) this.f5508d;
        c cVar3 = (c) this.f5509e[0];
        c cVarM = M();
        int[] iArrH = g.h();
        b.j(cVar.f5281i, iArrH);
        b.i(g.b(iArrH, iArrH, iArrH) + g.d(cVarM.f5281i, iArrH), iArrH);
        int[] iArrH2 = g.h();
        b.o(cVar2.f5281i, iArrH2);
        int[] iArrH3 = g.h();
        b.e(iArrH2, cVar2.f5281i, iArrH3);
        int[] iArrH4 = g.h();
        b.e(iArrH3, cVar.f5281i, iArrH4);
        b.o(iArrH4, iArrH4);
        int[] iArrH5 = g.h();
        b.j(iArrH3, iArrH5);
        b.o(iArrH5, iArrH5);
        c cVar4 = new c(iArrH3);
        b.j(iArrH, cVar4.f5281i);
        int[] iArr = cVar4.f5281i;
        b.n(iArr, iArrH4, iArr);
        int[] iArr2 = cVar4.f5281i;
        b.n(iArr2, iArrH4, iArr2);
        c cVar5 = new c(iArrH4);
        b.n(iArrH4, cVar4.f5281i, cVar5.f5281i);
        int[] iArr3 = cVar5.f5281i;
        b.e(iArr3, iArrH, iArr3);
        int[] iArr4 = cVar5.f5281i;
        b.n(iArr4, iArrH5, iArr4);
        c cVar6 = new c(iArrH2);
        if (!g.t(cVar3.f5281i)) {
            int[] iArr5 = cVar6.f5281i;
            b.e(iArr5, cVar3.f5281i, iArr5);
        }
        c cVar7 = null;
        if (z) {
            cVar7 = new c(iArrH5);
            int[] iArr6 = cVar7.f5281i;
            b.e(iArr6, cVarM.f5281i, iArr6);
            int[] iArr7 = cVar7.f5281i;
            b.o(iArr7, iArr7);
        }
        return new d(i(), cVar4, cVar5, new e[]{cVar6, cVar7}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public h a(h hVar) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (u()) {
            return hVar;
        }
        if (hVar.u()) {
            return this;
        }
        if (this == hVar) {
            return J();
        }
        g.a.d.b.d dVarI = i();
        c cVar = (c) this.f5507c;
        c cVar2 = (c) this.f5508d;
        c cVar3 = (c) this.f5509e[0];
        c cVar4 = (c) hVar.q();
        c cVar5 = (c) hVar.r();
        c cVar6 = (c) hVar.s(0);
        int[] iArrJ = g.j();
        int[] iArrH = g.h();
        int[] iArrH2 = g.h();
        int[] iArrH3 = g.h();
        boolean zH = cVar3.h();
        if (zH) {
            iArr = cVar4.f5281i;
            iArr2 = cVar5.f5281i;
        } else {
            b.j(cVar3.f5281i, iArrH2);
            b.e(iArrH2, cVar4.f5281i, iArrH);
            b.e(iArrH2, cVar3.f5281i, iArrH2);
            b.e(iArrH2, cVar5.f5281i, iArrH2);
            iArr = iArrH;
            iArr2 = iArrH2;
        }
        boolean zH2 = cVar6.h();
        if (zH2) {
            iArr3 = cVar.f5281i;
            iArr4 = cVar2.f5281i;
        } else {
            b.j(cVar6.f5281i, iArrH3);
            b.e(iArrH3, cVar.f5281i, iArrJ);
            b.e(iArrH3, cVar6.f5281i, iArrH3);
            b.e(iArrH3, cVar2.f5281i, iArrH3);
            iArr3 = iArrJ;
            iArr4 = iArrH3;
        }
        int[] iArrH4 = g.h();
        b.n(iArr3, iArr, iArrH4);
        b.n(iArr4, iArr2, iArrH);
        if (g.v(iArrH4)) {
            return g.v(iArrH) ? J() : dVarI.v();
        }
        int[] iArrH5 = g.h();
        b.j(iArrH4, iArrH5);
        int[] iArrH6 = g.h();
        b.e(iArrH5, iArrH4, iArrH6);
        b.e(iArrH5, iArr3, iArrH2);
        b.g(iArrH6, iArrH6);
        g.y(iArr4, iArrH6, iArrJ);
        b.i(g.b(iArrH2, iArrH2, iArrH6), iArrH6);
        c cVar7 = new c(iArrH3);
        b.j(iArrH, cVar7.f5281i);
        int[] iArr5 = cVar7.f5281i;
        b.n(iArr5, iArrH6, iArr5);
        c cVar8 = new c(iArrH6);
        b.n(iArrH2, cVar7.f5281i, cVar8.f5281i);
        b.f(cVar8.f5281i, iArrH, iArrJ);
        b.h(iArrJ, cVar8.f5281i);
        c cVar9 = new c(iArrH4);
        if (!zH) {
            int[] iArr6 = cVar9.f5281i;
            b.e(iArr6, cVar3.f5281i, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = cVar9.f5281i;
            b.e(iArr7, cVar6.f5281i, iArr7);
        }
        if (!zH || !zH2) {
            iArrH5 = null;
        }
        return new d(dVarI, cVar7, cVar8, new e[]{cVar9, L(cVar9, iArrH5)}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public h d() {
        return new d(null, f(), g());
    }

    @Override // g.a.d.b.h
    public e s(int i2) {
        return i2 == 1 ? M() : super.s(i2);
    }

    @Override // g.a.d.b.h
    public h z() {
        return u() ? this : new d(i(), this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
