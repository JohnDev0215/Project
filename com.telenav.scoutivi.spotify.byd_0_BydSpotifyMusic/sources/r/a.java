package r;

import java.util.HashMap;
import r.d;
import r.e;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends j {
    private int W0 = 0;
    private boolean X0 = true;
    private int Y0 = 0;
    boolean Z0 = false;

    public int A1() {
        int i7 = this.W0;
        if (i7 == 0 || i7 == 1) {
            return 0;
        }
        return (i7 == 2 || i7 == 3) ? 1 : -1;
    }

    protected void B1() {
        for (int i7 = 0; i7 < this.V0; i7++) {
            e eVar = this.U0[i7];
            if (this.X0 || eVar.h()) {
                int i8 = this.W0;
                if (i8 == 0 || i8 == 1) {
                    eVar.V0(0, true);
                } else if (i8 == 2 || i8 == 3) {
                    eVar.V0(1, true);
                }
            }
        }
    }

    public void C1(boolean z6) {
        this.X0 = z6;
    }

    public void D1(int i7) {
        this.W0 = i7;
    }

    public void E1(int i7) {
        this.Y0 = i7;
    }

    @Override // r.e
    public void g(o.d dVar, boolean z6) {
        d[] dVarArr;
        boolean z7;
        int i7;
        int i8;
        int i9;
        d[] dVarArr2 = this.Y;
        dVarArr2[0] = this.Q;
        dVarArr2[2] = this.R;
        dVarArr2[1] = this.S;
        dVarArr2[3] = this.T;
        int i10 = 0;
        while (true) {
            dVarArr = this.Y;
            if (i10 >= dVarArr.length) {
                break;
            }
            dVarArr[i10].f9201i = dVar.q(dVarArr[i10]);
            i10++;
        }
        int i11 = this.W0;
        if (i11 < 0 || i11 >= 4) {
            return;
        }
        d dVar2 = dVarArr[i11];
        if (!this.Z0) {
            w1();
        }
        if (this.Z0) {
            this.Z0 = false;
            int i12 = this.W0;
            if (i12 == 0 || i12 == 1) {
                dVar.f(this.Q.f9201i, this.f9219h0);
                dVar.f(this.S.f9201i, this.f9219h0);
                return;
            } else {
                if (i12 == 2 || i12 == 3) {
                    dVar.f(this.R.f9201i, this.f9221i0);
                    dVar.f(this.T.f9201i, this.f9221i0);
                    return;
                }
                return;
            }
        }
        for (int i13 = 0; i13 < this.V0; i13++) {
            e eVar = this.U0[i13];
            if ((this.X0 || eVar.h()) && ((((i8 = this.W0) == 0 || i8 == 1) && eVar.C() == e.b.MATCH_CONSTRAINT && eVar.Q.f9198f != null && eVar.S.f9198f != null) || (((i9 = this.W0) == 2 || i9 == 3) && eVar.V() == e.b.MATCH_CONSTRAINT && eVar.R.f9198f != null && eVar.T.f9198f != null))) {
                z7 = true;
                break;
            }
        }
        z7 = false;
        boolean z8 = this.Q.l() || this.S.l();
        boolean z9 = this.R.l() || this.T.l();
        int i14 = !z7 && (((i7 = this.W0) == 0 && z8) || ((i7 == 2 && z9) || ((i7 == 1 && z8) || (i7 == 3 && z9)))) ? 5 : 4;
        for (int i15 = 0; i15 < this.V0; i15++) {
            e eVar2 = this.U0[i15];
            if (this.X0 || eVar2.h()) {
                o.i iVarQ = dVar.q(eVar2.Y[this.W0]);
                d[] dVarArr3 = eVar2.Y;
                int i16 = this.W0;
                dVarArr3[i16].f9201i = iVarQ;
                int i17 = (dVarArr3[i16].f9198f == null || dVarArr3[i16].f9198f.f9196d != this) ? 0 : dVarArr3[i16].f9199g + 0;
                if (i16 == 0 || i16 == 2) {
                    dVar.i(dVar2.f9201i, iVarQ, this.Y0 - i17, z7);
                } else {
                    dVar.g(dVar2.f9201i, iVarQ, this.Y0 + i17, z7);
                }
                dVar.e(dVar2.f9201i, iVarQ, this.Y0 + i17, i14);
            }
        }
        int i18 = this.W0;
        if (i18 == 0) {
            dVar.e(this.S.f9201i, this.Q.f9201i, 0, 8);
            dVar.e(this.Q.f9201i, this.f9209c0.S.f9201i, 0, 4);
            dVar.e(this.Q.f9201i, this.f9209c0.Q.f9201i, 0, 0);
            return;
        }
        if (i18 == 1) {
            dVar.e(this.Q.f9201i, this.S.f9201i, 0, 8);
            dVar.e(this.Q.f9201i, this.f9209c0.Q.f9201i, 0, 4);
            dVar.e(this.Q.f9201i, this.f9209c0.S.f9201i, 0, 0);
        } else if (i18 == 2) {
            dVar.e(this.T.f9201i, this.R.f9201i, 0, 8);
            dVar.e(this.R.f9201i, this.f9209c0.T.f9201i, 0, 4);
            dVar.e(this.R.f9201i, this.f9209c0.R.f9201i, 0, 0);
        } else if (i18 == 3) {
            dVar.e(this.R.f9201i, this.T.f9201i, 0, 8);
            dVar.e(this.R.f9201i, this.f9209c0.R.f9201i, 0, 4);
            dVar.e(this.R.f9201i, this.f9209c0.T.f9201i, 0, 0);
        }
    }

    @Override // r.e
    public boolean h() {
        return true;
    }

    @Override // r.j, r.e
    public void n(e eVar, HashMap<e, e> map) {
        super.n(eVar, map);
        a aVar = (a) eVar;
        this.W0 = aVar.W0;
        this.X0 = aVar.X0;
        this.Y0 = aVar.Y0;
    }

    @Override // r.e
    public boolean p0() {
        return this.Z0;
    }

    @Override // r.e
    public boolean q0() {
        return this.Z0;
    }

    @Override // r.e
    public String toString() {
        String str = "[Barrier] " + v() + " {";
        for (int i7 = 0; i7 < this.V0; i7++) {
            e eVar = this.U0[i7];
            if (i7 > 0) {
                str = str + ", ";
            }
            str = str + eVar.v();
        }
        return str + "}";
    }

    public boolean w1() {
        int i7;
        int i8;
        int i9;
        int i10 = 0;
        boolean z6 = true;
        while (true) {
            i7 = this.V0;
            if (i10 >= i7) {
                break;
            }
            e eVar = this.U0[i10];
            if ((this.X0 || eVar.h()) && ((((i8 = this.W0) == 0 || i8 == 1) && !eVar.p0()) || (((i9 = this.W0) == 2 || i9 == 3) && !eVar.q0()))) {
                z6 = false;
            }
            i10++;
        }
        if (!z6 || i7 <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z7 = false;
        for (int i11 = 0; i11 < this.V0; i11++) {
            e eVar2 = this.U0[i11];
            if (this.X0 || eVar2.h()) {
                if (!z7) {
                    int i12 = this.W0;
                    if (i12 == 0) {
                        iMax = eVar2.q(d.b.LEFT).e();
                    } else if (i12 == 1) {
                        iMax = eVar2.q(d.b.RIGHT).e();
                    } else if (i12 == 2) {
                        iMax = eVar2.q(d.b.TOP).e();
                    } else if (i12 == 3) {
                        iMax = eVar2.q(d.b.BOTTOM).e();
                    }
                    z7 = true;
                }
                int i13 = this.W0;
                if (i13 == 0) {
                    iMax = Math.min(iMax, eVar2.q(d.b.LEFT).e());
                } else if (i13 == 1) {
                    iMax = Math.max(iMax, eVar2.q(d.b.RIGHT).e());
                } else if (i13 == 2) {
                    iMax = Math.min(iMax, eVar2.q(d.b.TOP).e());
                } else if (i13 == 3) {
                    iMax = Math.max(iMax, eVar2.q(d.b.BOTTOM).e());
                }
            }
        }
        int i14 = iMax + this.Y0;
        int i15 = this.W0;
        if (i15 == 0 || i15 == 1) {
            I0(i14, i14);
        } else {
            L0(i14, i14);
        }
        this.Z0 = true;
        return true;
    }

    public boolean x1() {
        return this.X0;
    }

    public int y1() {
        return this.W0;
    }

    public int z1() {
        return this.Y0;
    }
}
