package a.f.a.i;

import a.f.a.i.c;
import a.f.a.i.d;

/* JADX INFO: loaded from: classes.dex */
public class f extends d {
    public boolean A0;
    public float v0 = -1.0f;
    public int w0 = -1;
    public int x0 = -1;
    public c y0 = this.M;
    public int z0 = 0;

    public f() {
        this.U.clear();
        this.U.add(this.y0);
        int length = this.T.length;
        for (int i = 0; i < length; i++) {
            this.T[i] = this.y0;
        }
    }

    @Override // a.f.a.i.d
    public boolean D() {
        return this.A0;
    }

    @Override // a.f.a.i.d
    public boolean E() {
        return this.A0;
    }

    @Override // a.f.a.i.d
    public void U(a.f.a.d dVar, boolean z) {
        if (this.X == null) {
            return;
        }
        int iO = dVar.o(this.y0);
        if (this.z0 == 1) {
            this.c0 = iO;
            this.d0 = 0;
            N(this.X.l());
            S(0);
            return;
        }
        this.c0 = 0;
        this.d0 = iO;
        S(this.X.u());
        N(0);
    }

    public void V(int i) {
        c cVar = this.y0;
        cVar.f544b = i;
        cVar.f545c = true;
        this.A0 = true;
    }

    public void W(int i) {
        if (this.z0 == i) {
            return;
        }
        this.z0 = i;
        this.U.clear();
        this.y0 = this.z0 == 1 ? this.L : this.M;
        this.U.add(this.y0);
        int length = this.T.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.T[i2] = this.y0;
        }
    }

    @Override // a.f.a.i.d
    public void d(a.f.a.d dVar, boolean z) {
        a.f.a.g gVarL;
        d.a aVar = d.a.WRAP_CONTENT;
        e eVar = (e) this.X;
        if (eVar == null) {
            return;
        }
        Object objI = eVar.i(c.a.LEFT);
        Object objI2 = eVar.i(c.a.RIGHT);
        d dVar2 = this.X;
        boolean z2 = dVar2 != null && dVar2.W[0] == aVar;
        if (this.z0 == 0) {
            objI = eVar.i(c.a.TOP);
            objI2 = eVar.i(c.a.BOTTOM);
            d dVar3 = this.X;
            z2 = dVar3 != null && dVar3.W[1] == aVar;
        }
        if (this.A0) {
            c cVar = this.y0;
            if (cVar.f545c) {
                a.f.a.g gVarL2 = dVar.l(cVar);
                dVar.e(gVarL2, this.y0.c());
                if (this.w0 != -1) {
                    if (z2) {
                        gVarL = dVar.l(objI2);
                        dVar.f(gVarL, gVarL2, 0, 5);
                    }
                } else if (this.x0 != -1 && z2) {
                    gVarL = dVar.l(objI2);
                    dVar.f(gVarL2, dVar.l(objI), 0, 5);
                    dVar.f(gVarL, gVarL2, 0, 5);
                }
                this.A0 = false;
                return;
            }
        }
        if (this.w0 != -1) {
            a.f.a.g gVarL3 = dVar.l(this.y0);
            dVar.d(gVarL3, dVar.l(objI), this.w0, 8);
            if (z2) {
                dVar.f(dVar.l(objI2), gVarL3, 0, 5);
                return;
            }
            return;
        }
        if (this.x0 != -1) {
            a.f.a.g gVarL4 = dVar.l(this.y0);
            a.f.a.g gVarL5 = dVar.l(objI2);
            dVar.d(gVarL4, gVarL5, -this.x0, 8);
            if (z2) {
                dVar.f(gVarL4, dVar.l(objI), 0, 5);
                dVar.f(gVarL5, gVarL4, 0, 5);
                return;
            }
            return;
        }
        if (this.v0 != -1.0f) {
            a.f.a.g gVarL6 = dVar.l(this.y0);
            a.f.a.g gVarL7 = dVar.l(objI2);
            float f2 = this.v0;
            a.f.a.b bVarM = dVar.m();
            bVarM.f506e.j(gVarL6, -1.0f);
            bVarM.f506e.j(gVarL7, f2);
            dVar.c(bVarM);
        }
    }

    @Override // a.f.a.i.d
    public boolean e() {
        return true;
    }

    @Override // a.f.a.i.d
    public c i(c.a aVar) {
        int iOrdinal = aVar.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        return null;
                    }
                }
            }
            if (this.z0 == 0) {
                return this.y0;
            }
            return null;
        }
        if (this.z0 == 1) {
            return this.y0;
        }
        return null;
    }
}
