package g.a.d.b.a0.b;

import g.a.d.b.e;
import g.a.d.b.h;
import g.a.d.d.g;
import g.a.d.d.m;

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
        return (u() || this.f5508d.i()) ? this : J().a(this);
    }

    @Override // g.a.d.b.h
    public h J() {
        if (u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        c cVar = (c) this.f5508d;
        if (cVar.i()) {
            return dVarI.v();
        }
        c cVar2 = (c) this.f5507c;
        c cVar3 = (c) this.f5509e[0];
        int[] iArrH = g.h();
        int[] iArrH2 = g.h();
        int[] iArrH3 = g.h();
        b.j(cVar.f5289h, iArrH3);
        int[] iArrH4 = g.h();
        b.j(iArrH3, iArrH4);
        boolean zH = cVar3.h();
        int[] iArr = cVar3.f5289h;
        if (!zH) {
            b.j(iArr, iArrH2);
            iArr = iArrH2;
        }
        b.m(cVar2.f5289h, iArr, iArrH);
        b.a(cVar2.f5289h, iArr, iArrH2);
        b.e(iArrH2, iArrH, iArrH2);
        b.i(g.b(iArrH2, iArrH2, iArrH2), iArrH2);
        b.e(iArrH3, cVar2.f5289h, iArrH3);
        b.i(m.G(8, iArrH3, 2, 0), iArrH3);
        b.i(m.H(8, iArrH4, 3, 0, iArrH), iArrH);
        c cVar4 = new c(iArrH4);
        b.j(iArrH2, cVar4.f5289h);
        int[] iArr2 = cVar4.f5289h;
        b.m(iArr2, iArrH3, iArr2);
        int[] iArr3 = cVar4.f5289h;
        b.m(iArr3, iArrH3, iArr3);
        c cVar5 = new c(iArrH3);
        b.m(iArrH3, cVar4.f5289h, cVar5.f5289h);
        int[] iArr4 = cVar5.f5289h;
        b.e(iArr4, iArrH2, iArr4);
        int[] iArr5 = cVar5.f5289h;
        b.m(iArr5, iArrH, iArr5);
        c cVar6 = new c(iArrH2);
        b.n(cVar.f5289h, cVar6.f5289h);
        if (!zH) {
            int[] iArr6 = cVar6.f5289h;
            b.e(iArr6, cVar3.f5289h, iArr6);
        }
        return new d(dVarI, cVar4, cVar5, new e[]{cVar6}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public h K(h hVar) {
        return this == hVar ? H() : u() ? hVar : hVar.u() ? J() : this.f5508d.i() ? hVar : J().a(hVar);
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
        c cVar3 = (c) hVar.q();
        c cVar4 = (c) hVar.r();
        c cVar5 = (c) this.f5509e[0];
        c cVar6 = (c) hVar.s(0);
        int[] iArrJ = g.j();
        int[] iArrH = g.h();
        int[] iArrH2 = g.h();
        int[] iArrH3 = g.h();
        boolean zH = cVar5.h();
        if (zH) {
            iArr = cVar3.f5289h;
            iArr2 = cVar4.f5289h;
        } else {
            b.j(cVar5.f5289h, iArrH2);
            b.e(iArrH2, cVar3.f5289h, iArrH);
            b.e(iArrH2, cVar5.f5289h, iArrH2);
            b.e(iArrH2, cVar4.f5289h, iArrH2);
            iArr = iArrH;
            iArr2 = iArrH2;
        }
        boolean zH2 = cVar6.h();
        if (zH2) {
            iArr3 = cVar.f5289h;
            iArr4 = cVar2.f5289h;
        } else {
            b.j(cVar6.f5289h, iArrH3);
            b.e(iArrH3, cVar.f5289h, iArrJ);
            b.e(iArrH3, cVar6.f5289h, iArrH3);
            b.e(iArrH3, cVar2.f5289h, iArrH3);
            iArr3 = iArrJ;
            iArr4 = iArrH3;
        }
        int[] iArrH4 = g.h();
        b.m(iArr3, iArr, iArrH4);
        b.m(iArr4, iArr2, iArrH);
        if (g.v(iArrH4)) {
            return g.v(iArrH) ? J() : dVarI.v();
        }
        b.j(iArrH4, iArrH2);
        int[] iArrH5 = g.h();
        b.e(iArrH2, iArrH4, iArrH5);
        b.e(iArrH2, iArr3, iArrH2);
        b.g(iArrH5, iArrH5);
        g.y(iArr4, iArrH5, iArrJ);
        b.i(g.b(iArrH2, iArrH2, iArrH5), iArrH5);
        c cVar7 = new c(iArrH3);
        b.j(iArrH, cVar7.f5289h);
        int[] iArr5 = cVar7.f5289h;
        b.m(iArr5, iArrH5, iArr5);
        c cVar8 = new c(iArrH5);
        b.m(iArrH2, cVar7.f5289h, cVar8.f5289h);
        b.f(cVar8.f5289h, iArrH, iArrJ);
        b.h(iArrJ, cVar8.f5289h);
        c cVar9 = new c(iArrH4);
        if (!zH) {
            int[] iArr6 = cVar9.f5289h;
            b.e(iArr6, cVar5.f5289h, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = cVar9.f5289h;
            b.e(iArr7, cVar6.f5289h, iArr7);
        }
        return new d(dVarI, cVar7, cVar8, new e[]{cVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public h d() {
        return new d(null, f(), g());
    }

    @Override // g.a.d.b.h
    public h z() {
        return u() ? this : new d(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
