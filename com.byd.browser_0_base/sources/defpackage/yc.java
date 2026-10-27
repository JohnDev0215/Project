package defpackage;

import defpackage.nx;
import defpackage.qx;
import java.util.HashMap;

/* JADX INFO: compiled from: Barrier.java */
/* JADX INFO: loaded from: classes.dex */
public final class yc extends ku0 {
    public int y0 = 0;
    public boolean z0 = true;
    public int A0 = 0;
    public boolean B0 = false;

    @Override // defpackage.qx
    public final boolean E() {
        return this.B0;
    }

    @Override // defpackage.qx
    public final boolean F() {
        return this.B0;
    }

    public final boolean V() {
        int i;
        int i2;
        int i3;
        boolean z = true;
        int i4 = 0;
        while (true) {
            i = this.x0;
            if (i4 >= i) {
                break;
            }
            qx qxVar = this.w0[i4];
            if ((this.z0 || qxVar.f()) && ((((i2 = this.y0) == 0 || i2 == 1) && !qxVar.E()) || (((i3 = this.y0) == 2 || i3 == 3) && !qxVar.F()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.x0; i5++) {
            qx qxVar2 = this.w0[i5];
            if (this.z0 || qxVar2.f()) {
                nx.a aVar = nx.a.BOTTOM;
                nx.a aVar2 = nx.a.TOP;
                nx.a aVar3 = nx.a.RIGHT;
                nx.a aVar4 = nx.a.LEFT;
                if (!z2) {
                    int i6 = this.y0;
                    if (i6 == 0) {
                        iMax = qxVar2.m(aVar4).d();
                    } else if (i6 == 1) {
                        iMax = qxVar2.m(aVar3).d();
                    } else if (i6 == 2) {
                        iMax = qxVar2.m(aVar2).d();
                    } else if (i6 == 3) {
                        iMax = qxVar2.m(aVar).d();
                    }
                    z2 = true;
                }
                int i7 = this.y0;
                if (i7 == 0) {
                    iMax = Math.min(iMax, qxVar2.m(aVar4).d());
                } else if (i7 == 1) {
                    iMax = Math.max(iMax, qxVar2.m(aVar3).d());
                } else if (i7 == 2) {
                    iMax = Math.min(iMax, qxVar2.m(aVar2).d());
                } else if (i7 == 3) {
                    iMax = Math.max(iMax, qxVar2.m(aVar).d());
                }
            }
        }
        int i8 = iMax + this.A0;
        int i9 = this.y0;
        if (i9 == 0 || i9 == 1) {
            M(i8, i8);
        } else {
            N(i8, i8);
        }
        this.B0 = true;
        return true;
    }

    public final int W() {
        int i = this.y0;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    @Override // defpackage.qx
    public final void e(q71 q71Var, boolean z) {
        boolean z2;
        int i;
        nx[] nxVarArr = this.S;
        nx nxVar = this.K;
        nxVarArr[0] = nxVar;
        int i2 = 2;
        nx nxVar2 = this.L;
        nxVarArr[2] = nxVar2;
        nx nxVar3 = this.M;
        nxVarArr[1] = nxVar3;
        nx nxVar4 = this.N;
        nxVarArr[3] = nxVar4;
        for (nx nxVar5 : nxVarArr) {
            nxVar5.i = q71Var.k(nxVar5);
        }
        int i3 = this.y0;
        if (i3 < 0 || i3 >= 4) {
            return;
        }
        nx nxVar6 = nxVarArr[i3];
        if (!this.B0) {
            V();
        }
        if (this.B0) {
            this.B0 = false;
            int i4 = this.y0;
            if (i4 == 0 || i4 == 1) {
                q71Var.d(nxVar.i, this.b0);
                q71Var.d(nxVar3.i, this.b0);
                return;
            } else {
                if (i4 == 2 || i4 == 3) {
                    q71Var.d(nxVar2.i, this.c0);
                    q71Var.d(nxVar4.i, this.c0);
                    return;
                }
                return;
            }
        }
        for (int i5 = 0; i5 < this.x0; i5++) {
            qx qxVar = this.w0[i5];
            if (this.z0 || qxVar.f()) {
                int i6 = this.y0;
                qx.a aVar = qx.a.MATCH_CONSTRAINT;
                if (((i6 == 0 || i6 == 1) && qxVar.V[0] == aVar && qxVar.K.f != null && qxVar.M.f != null) || ((i6 == 2 || i6 == 3) && qxVar.V[1] == aVar && qxVar.L.f != null && qxVar.N.f != null)) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        boolean z3 = nxVar.g() || nxVar3.g();
        boolean z4 = nxVar2.g() || nxVar4.g();
        int i7 = !(!z2 && (((i = this.y0) == 0 && z3) || ((i == 2 && z4) || ((i == 1 && z3) || (i == 3 && z4))))) ? 4 : 5;
        int i8 = 0;
        while (i8 < this.x0) {
            qx qxVar2 = this.w0[i8];
            if (this.z0 || qxVar2.f()) {
                hq2 hq2VarK = q71Var.k(qxVar2.S[this.y0]);
                int i9 = this.y0;
                nx nxVar7 = qxVar2.S[i9];
                nxVar7.i = hq2VarK;
                nx nxVar8 = nxVar7.f;
                int i10 = (nxVar8 == null || nxVar8.d != this) ? 0 : nxVar7.g + 0;
                if (i9 == 0 || i9 == i2) {
                    hq2 hq2Var = nxVar6.i;
                    int i11 = this.A0 - i10;
                    p8 p8VarL = q71Var.l();
                    hq2 hq2VarM = q71Var.m();
                    hq2VarM.h = 0;
                    p8VarL.d(hq2Var, hq2VarK, hq2VarM, i11);
                    q71Var.c(p8VarL);
                } else {
                    hq2 hq2Var2 = nxVar6.i;
                    int i12 = this.A0 + i10;
                    p8 p8VarL2 = q71Var.l();
                    hq2 hq2VarM2 = q71Var.m();
                    hq2VarM2.h = 0;
                    p8VarL2.c(hq2Var2, hq2VarK, hq2VarM2, i12);
                    q71Var.c(p8VarL2);
                }
                q71Var.e(nxVar6.i, hq2VarK, this.A0 + i10, i7);
            }
            i8++;
            i2 = 2;
        }
        int i13 = this.y0;
        if (i13 == 0) {
            q71Var.e(nxVar3.i, nxVar.i, 0, 8);
            q71Var.e(nxVar.i, this.W.M.i, 0, 4);
            q71Var.e(nxVar.i, this.W.K.i, 0, 0);
            return;
        }
        if (i13 == 1) {
            q71Var.e(nxVar.i, nxVar3.i, 0, 8);
            q71Var.e(nxVar.i, this.W.K.i, 0, 4);
            q71Var.e(nxVar.i, this.W.M.i, 0, 0);
        } else if (i13 == 2) {
            q71Var.e(nxVar4.i, nxVar2.i, 0, 8);
            q71Var.e(nxVar2.i, this.W.N.i, 0, 4);
            q71Var.e(nxVar2.i, this.W.L.i, 0, 0);
        } else if (i13 == 3) {
            q71Var.e(nxVar2.i, nxVar4.i, 0, 8);
            q71Var.e(nxVar2.i, this.W.L.i, 0, 4);
            q71Var.e(nxVar2.i, this.W.N.i, 0, 0);
        }
    }

    @Override // defpackage.qx
    public final boolean f() {
        return true;
    }

    @Override // defpackage.ku0, defpackage.qx
    public final void j(qx qxVar, HashMap<qx, qx> map) {
        super.j(qxVar, map);
        yc ycVar = (yc) qxVar;
        this.y0 = ycVar.y0;
        this.z0 = ycVar.z0;
        this.A0 = ycVar.A0;
    }

    @Override // defpackage.qx
    public final String toString() {
        String strI = gq2.i(new StringBuilder("[Barrier] "), this.l0, " {");
        for (int i = 0; i < this.x0; i++) {
            qx qxVar = this.w0[i];
            if (i > 0) {
                strI = gq2.f(strI, ", ");
            }
            StringBuilder sbJ = C0173a.j(strI);
            sbJ.append(qxVar.l0);
            strI = sbJ.toString();
        }
        return gq2.f(strI, "}");
    }
}
