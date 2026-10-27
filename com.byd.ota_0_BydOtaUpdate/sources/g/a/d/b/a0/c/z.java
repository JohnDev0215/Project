package g.a.d.b.a0.c;

import g.a.d.b.h;

/* JADX INFO: loaded from: classes.dex */
public class z extends h.c {
    public z(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2) {
        this(dVar, eVar, eVar2, false);
    }

    public z(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        super(dVar, eVar, eVar2);
        if ((eVar == null) != (eVar2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.f5510f = z;
    }

    public z(g.a.d.b.d dVar, g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        super(dVar, eVar, eVar2, eVarArr);
        this.f5510f = z;
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h H() {
        return (u() || this.f5508d.i()) ? this : J().a(this);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h J() {
        if (u()) {
            return this;
        }
        g.a.d.b.d dVarI = i();
        y yVar = (y) this.f5508d;
        if (yVar.i()) {
            return dVarI.v();
        }
        y yVar2 = (y) this.f5507c;
        y yVar3 = (y) this.f5509e[0];
        int[] iArrE = g.a.d.d.f.e();
        x.i(yVar.f5451i, iArrE);
        int[] iArrE2 = g.a.d.d.f.e();
        x.i(iArrE, iArrE2);
        int[] iArrE3 = g.a.d.d.f.e();
        x.i(yVar2.f5451i, iArrE3);
        x.h(g.a.d.d.f.b(iArrE3, iArrE3, iArrE3), iArrE3);
        x.d(iArrE, yVar2.f5451i, iArrE);
        x.h(g.a.d.d.m.G(7, iArrE, 2, 0), iArrE);
        int[] iArrE4 = g.a.d.d.f.e();
        x.h(g.a.d.d.m.H(7, iArrE2, 3, 0, iArrE4), iArrE4);
        y yVar4 = new y(iArrE2);
        x.i(iArrE3, yVar4.f5451i);
        int[] iArr = yVar4.f5451i;
        x.k(iArr, iArrE, iArr);
        int[] iArr2 = yVar4.f5451i;
        x.k(iArr2, iArrE, iArr2);
        y yVar5 = new y(iArrE);
        x.k(iArrE, yVar4.f5451i, yVar5.f5451i);
        int[] iArr3 = yVar5.f5451i;
        x.d(iArr3, iArrE3, iArr3);
        int[] iArr4 = yVar5.f5451i;
        x.k(iArr4, iArrE4, iArr4);
        y yVar6 = new y(iArrE3);
        x.l(yVar.f5451i, yVar6.f5451i);
        if (!yVar3.h()) {
            int[] iArr5 = yVar6.f5451i;
            x.d(iArr5, yVar3.f5451i, iArr5);
        }
        return new z(dVarI, yVar4, yVar5, new g.a.d.b.e[]{yVar6}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h K(g.a.d.b.h hVar) {
        return this == hVar ? H() : u() ? hVar : hVar.u() ? J() : this.f5508d.i() ? hVar : J().a(hVar);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h a(g.a.d.b.h hVar) {
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
        y yVar = (y) this.f5507c;
        y yVar2 = (y) this.f5508d;
        y yVar3 = (y) hVar.q();
        y yVar4 = (y) hVar.r();
        y yVar5 = (y) this.f5509e[0];
        y yVar6 = (y) hVar.s(0);
        int[] iArrF = g.a.d.d.f.f();
        int[] iArrE = g.a.d.d.f.e();
        int[] iArrE2 = g.a.d.d.f.e();
        int[] iArrE3 = g.a.d.d.f.e();
        boolean zH = yVar5.h();
        if (zH) {
            iArr = yVar3.f5451i;
            iArr2 = yVar4.f5451i;
        } else {
            x.i(yVar5.f5451i, iArrE2);
            x.d(iArrE2, yVar3.f5451i, iArrE);
            x.d(iArrE2, yVar5.f5451i, iArrE2);
            x.d(iArrE2, yVar4.f5451i, iArrE2);
            iArr = iArrE;
            iArr2 = iArrE2;
        }
        boolean zH2 = yVar6.h();
        if (zH2) {
            iArr3 = yVar.f5451i;
            iArr4 = yVar2.f5451i;
        } else {
            x.i(yVar6.f5451i, iArrE3);
            x.d(iArrE3, yVar.f5451i, iArrF);
            x.d(iArrE3, yVar6.f5451i, iArrE3);
            x.d(iArrE3, yVar2.f5451i, iArrE3);
            iArr3 = iArrF;
            iArr4 = iArrE3;
        }
        int[] iArrE4 = g.a.d.d.f.e();
        x.k(iArr3, iArr, iArrE4);
        x.k(iArr4, iArr2, iArrE);
        if (g.a.d.d.f.l(iArrE4)) {
            return g.a.d.d.f.l(iArrE) ? J() : dVarI.v();
        }
        x.i(iArrE4, iArrE2);
        int[] iArrE5 = g.a.d.d.f.e();
        x.d(iArrE2, iArrE4, iArrE5);
        x.d(iArrE2, iArr3, iArrE2);
        x.f(iArrE5, iArrE5);
        g.a.d.d.f.m(iArr4, iArrE5, iArrF);
        x.h(g.a.d.d.f.b(iArrE2, iArrE2, iArrE5), iArrE5);
        y yVar7 = new y(iArrE3);
        x.i(iArrE, yVar7.f5451i);
        int[] iArr5 = yVar7.f5451i;
        x.k(iArr5, iArrE5, iArr5);
        y yVar8 = new y(iArrE5);
        x.k(iArrE2, yVar7.f5451i, yVar8.f5451i);
        x.e(yVar8.f5451i, iArrE, iArrF);
        x.g(iArrF, yVar8.f5451i);
        y yVar9 = new y(iArrE4);
        if (!zH) {
            int[] iArr6 = yVar9.f5451i;
            x.d(iArr6, yVar5.f5451i, iArr6);
        }
        if (!zH2) {
            int[] iArr7 = yVar9.f5451i;
            x.d(iArr7, yVar6.f5451i, iArr7);
        }
        return new z(dVarI, yVar7, yVar8, new g.a.d.b.e[]{yVar9}, this.f5510f);
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h d() {
        return new z(null, f(), g());
    }

    @Override // g.a.d.b.h
    public g.a.d.b.h z() {
        return u() ? this : new z(this.f5506b, this.f5507c, this.f5508d.m(), this.f5509e, this.f5510f);
    }
}
