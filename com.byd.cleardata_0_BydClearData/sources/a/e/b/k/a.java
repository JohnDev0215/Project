package a.e.b.k;

import a.e.b.k.e;

/* JADX INFO: loaded from: classes.dex */
public class a extends i {
    private int y0 = 0;
    private boolean z0 = true;
    private int A0 = 0;

    public boolean H0() {
        return this.z0;
    }

    public int I0() {
        return this.y0;
    }

    public int J0() {
        return this.A0;
    }

    protected void K0() {
        for (int i = 0; i < this.x0; i++) {
            e eVar = this.w0[i];
            int i2 = this.y0;
            if (i2 == 0 || i2 == 1) {
                eVar.l0(0, true);
            } else if (i2 == 2 || i2 == 3) {
                eVar.l0(1, true);
            }
        }
    }

    public void L0(boolean z) {
        this.z0 = z;
    }

    public void M0(int i) {
        this.y0 = i;
    }

    public void N0(int i) {
        this.A0 = i;
    }

    @Override // a.e.b.k.e
    public void f(a.e.b.d dVar) {
        d[] dVarArr;
        boolean z;
        a.e.b.i iVar;
        d dVar2;
        int i;
        int i2;
        int i3;
        d[] dVarArr2 = this.K;
        dVarArr2[0] = this.C;
        dVarArr2[2] = this.D;
        dVarArr2[1] = this.E;
        dVarArr2[3] = this.F;
        int i4 = 0;
        while (true) {
            dVarArr = this.K;
            if (i4 >= dVarArr.length) {
                break;
            }
            dVarArr[i4].g = dVar.q(dVarArr[i4]);
            i4++;
        }
        int i5 = this.y0;
        if (i5 < 0 || i5 >= 4) {
            return;
        }
        d dVar3 = dVarArr[i5];
        for (int i6 = 0; i6 < this.x0; i6++) {
            e eVar = this.w0[i6];
            if ((this.z0 || eVar.g()) && ((((i2 = this.y0) == 0 || i2 == 1) && eVar.w() == e.b.MATCH_CONSTRAINT && eVar.C.f207d != null && eVar.E.f207d != null) || (((i3 = this.y0) == 2 || i3 == 3) && eVar.K() == e.b.MATCH_CONSTRAINT && eVar.D.f207d != null && eVar.F.f207d != null))) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = this.C.h() || this.E.h();
        boolean z3 = this.D.h() || this.F.h();
        int i7 = !z && (((i = this.y0) == 0 && z2) || ((i == 2 && z3) || ((i == 1 && z2) || (i == 3 && z3)))) ? 5 : 4;
        for (int i8 = 0; i8 < this.x0; i8++) {
            e eVar2 = this.w0[i8];
            if (this.z0 || eVar2.g()) {
                a.e.b.i iVarQ = dVar.q(eVar2.K[this.y0]);
                d[] dVarArr3 = eVar2.K;
                int i9 = this.y0;
                dVarArr3[i9].g = iVarQ;
                int i10 = (dVarArr3[i9].f207d == null || dVarArr3[i9].f207d.f205b != this) ? 0 : dVarArr3[i9].e + 0;
                if (i9 == 0 || i9 == 2) {
                    dVar.i(dVar3.g, iVarQ, this.A0 - i10, z);
                } else {
                    dVar.g(dVar3.g, iVarQ, this.A0 + i10, z);
                }
                dVar.e(dVar3.g, iVarQ, this.A0 + i10, i7);
            }
        }
        int i11 = this.y0;
        if (i11 == 0) {
            dVar.e(this.E.g, this.C.g, 0, 8);
            dVar.e(this.C.g, this.O.E.g, 0, 4);
            iVar = this.C.g;
            dVar2 = this.O.C;
        } else if (i11 == 1) {
            dVar.e(this.C.g, this.E.g, 0, 8);
            dVar.e(this.C.g, this.O.C.g, 0, 4);
            iVar = this.C.g;
            dVar2 = this.O.E;
        } else if (i11 == 2) {
            dVar.e(this.F.g, this.D.g, 0, 8);
            dVar.e(this.D.g, this.O.F.g, 0, 4);
            iVar = this.D.g;
            dVar2 = this.O.D;
        } else {
            if (i11 != 3) {
                return;
            }
            dVar.e(this.D.g, this.F.g, 0, 8);
            dVar.e(this.D.g, this.O.D.g, 0, 4);
            iVar = this.D.g;
            dVar2 = this.O.F;
        }
        dVar.e(iVar, dVar2.g, 0, 0);
    }

    @Override // a.e.b.k.e
    public boolean g() {
        return true;
    }

    @Override // a.e.b.k.e
    public String toString() {
        String str = "[Barrier] " + p() + " {";
        for (int i = 0; i < this.x0; i++) {
            e eVar = this.w0[i];
            if (i > 0) {
                str = str + ", ";
            }
            str = str + eVar.p();
        }
        return str + "}";
    }
}
