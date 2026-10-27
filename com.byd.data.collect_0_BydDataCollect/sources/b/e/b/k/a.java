package b.e.b.k;

import b.e.b.k.e;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends j {
    private int I0 = 0;
    private boolean J0 = true;
    private int K0 = 0;

    public boolean L0() {
        return this.J0;
    }

    public int M0() {
        return this.I0;
    }

    public int N0() {
        return this.K0;
    }

    protected void O0() {
        for (int i2 = 0; i2 < this.H0; i2++) {
            e eVar = this.G0[i2];
            int i3 = this.I0;
            if (i3 == 0 || i3 == 1) {
                eVar.p0(0, true);
            } else if (i3 == 2 || i3 == 3) {
                eVar.p0(1, true);
            }
        }
    }

    public void P0(boolean z) {
        this.J0 = z;
    }

    public void Q0(int i2) {
        this.I0 = i2;
    }

    public void R0(int i2) {
        this.K0 = i2;
    }

    @Override // b.e.b.k.e
    public void f(b.e.b.d dVar) {
        d[] dVarArr;
        boolean z;
        int i2;
        int i3;
        int i4;
        d[] dVarArr2 = this.M;
        dVarArr2[0] = this.E;
        dVarArr2[2] = this.F;
        dVarArr2[1] = this.G;
        dVarArr2[3] = this.H;
        int i5 = 0;
        while (true) {
            dVarArr = this.M;
            if (i5 >= dVarArr.length) {
                break;
            }
            dVarArr[i5].f2428g = dVar.q(dVarArr[i5]);
            i5++;
        }
        int i6 = this.I0;
        if (i6 < 0 || i6 >= 4) {
            return;
        }
        d dVar2 = dVarArr[i6];
        for (int i7 = 0; i7 < this.H0; i7++) {
            e eVar = this.G0[i7];
            if ((this.J0 || eVar.g()) && ((((i3 = this.I0) == 0 || i3 == 1) && eVar.z() == e.b.MATCH_CONSTRAINT && eVar.E.f2425d != null && eVar.G.f2425d != null) || (((i4 = this.I0) == 2 || i4 == 3) && eVar.N() == e.b.MATCH_CONSTRAINT && eVar.F.f2425d != null && eVar.H.f2425d != null))) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = this.E.i() || this.G.i();
        boolean z3 = this.F.i() || this.H.i();
        int i8 = !z && (((i2 = this.I0) == 0 && z2) || ((i2 == 2 && z3) || ((i2 == 1 && z2) || (i2 == 3 && z3)))) ? 5 : 4;
        for (int i9 = 0; i9 < this.H0; i9++) {
            e eVar2 = this.G0[i9];
            if (this.J0 || eVar2.g()) {
                b.e.b.i iVarQ = dVar.q(eVar2.M[this.I0]);
                d[] dVarArr3 = eVar2.M;
                int i10 = this.I0;
                dVarArr3[i10].f2428g = iVarQ;
                int i11 = (dVarArr3[i10].f2425d == null || dVarArr3[i10].f2425d.f2423b != this) ? 0 : dVarArr3[i10].f2426e + 0;
                if (i10 == 0 || i10 == 2) {
                    dVar.i(dVar2.f2428g, iVarQ, this.K0 - i11, z);
                } else {
                    dVar.g(dVar2.f2428g, iVarQ, this.K0 + i11, z);
                }
                dVar.e(dVar2.f2428g, iVarQ, this.K0 + i11, i8);
            }
        }
        int i12 = this.I0;
        if (i12 == 0) {
            dVar.e(this.G.f2428g, this.E.f2428g, 0, 8);
            dVar.e(this.E.f2428g, this.Q.G.f2428g, 0, 4);
            dVar.e(this.E.f2428g, this.Q.E.f2428g, 0, 0);
            return;
        }
        if (i12 == 1) {
            dVar.e(this.E.f2428g, this.G.f2428g, 0, 8);
            dVar.e(this.E.f2428g, this.Q.E.f2428g, 0, 4);
            dVar.e(this.E.f2428g, this.Q.G.f2428g, 0, 0);
        } else if (i12 == 2) {
            dVar.e(this.H.f2428g, this.F.f2428g, 0, 8);
            dVar.e(this.F.f2428g, this.Q.H.f2428g, 0, 4);
            dVar.e(this.F.f2428g, this.Q.F.f2428g, 0, 0);
        } else if (i12 == 3) {
            dVar.e(this.F.f2428g, this.H.f2428g, 0, 8);
            dVar.e(this.F.f2428g, this.Q.F.f2428g, 0, 4);
            dVar.e(this.F.f2428g, this.Q.H.f2428g, 0, 0);
        }
    }

    @Override // b.e.b.k.e
    public boolean g() {
        return true;
    }

    @Override // b.e.b.k.j, b.e.b.k.e
    public void l(e eVar, HashMap<e, e> map) {
        super.l(eVar, map);
        a aVar = (a) eVar;
        this.I0 = aVar.I0;
        this.J0 = aVar.J0;
        this.K0 = aVar.K0;
    }

    @Override // b.e.b.k.e
    public String toString() {
        String str = "[Barrier] " + s() + " {";
        for (int i2 = 0; i2 < this.H0; i2++) {
            e eVar = this.G0[i2];
            if (i2 > 0) {
                str = str + ", ";
            }
            str = str + eVar.s();
        }
        return str + "}";
    }
}
