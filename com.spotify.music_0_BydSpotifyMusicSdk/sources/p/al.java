package p;

import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class al extends z11 {
    public int r0;
    public boolean s0;
    public int t0;
    public boolean u0;

    public final boolean O() {
        int i;
        int i2;
        int i3;
        boolean z = true;
        int i4 = 0;
        while (true) {
            i = this.q0;
            if (i4 >= i) {
                break;
            }
            ty tyVar = this.p0[i4];
            if ((this.s0 || tyVar.c()) && ((((i2 = this.r0) == 0 || i2 == 1) && !tyVar.y()) || (((i3 = this.r0) == 2 || i3 == 3) && !tyVar.z()))) {
                z = false;
            }
            i4++;
        }
        if (!z || i <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.q0; i5++) {
            ty tyVar2 = this.p0[i5];
            if (this.s0 || tyVar2.c()) {
                ConstraintAnchor$Type constraintAnchor$Type = ConstraintAnchor$Type.l;
                ConstraintAnchor$Type constraintAnchor$Type2 = ConstraintAnchor$Type.e;
                ConstraintAnchor$Type constraintAnchor$Type3 = ConstraintAnchor$Type.k;
                ConstraintAnchor$Type constraintAnchor$Type4 = ConstraintAnchor$Type.c;
                if (!z2) {
                    int i6 = this.r0;
                    if (i6 == 0) {
                        iMax = tyVar2.g(constraintAnchor$Type4).c();
                    } else if (i6 == 1) {
                        iMax = tyVar2.g(constraintAnchor$Type3).c();
                    } else if (i6 == 2) {
                        iMax = tyVar2.g(constraintAnchor$Type2).c();
                    } else if (i6 == 3) {
                        iMax = tyVar2.g(constraintAnchor$Type).c();
                    }
                    z2 = true;
                }
                int i7 = this.r0;
                if (i7 == 0) {
                    iMax = Math.min(iMax, tyVar2.g(constraintAnchor$Type4).c());
                } else if (i7 == 1) {
                    iMax = Math.max(iMax, tyVar2.g(constraintAnchor$Type3).c());
                } else if (i7 == 2) {
                    iMax = Math.min(iMax, tyVar2.g(constraintAnchor$Type2).c());
                } else if (i7 == 3) {
                    iMax = Math.max(iMax, tyVar2.g(constraintAnchor$Type).c());
                }
            }
        }
        int i8 = iMax + this.t0;
        int i9 = this.r0;
        if (i9 == 0 || i9 == 1) {
            F(i8, i8);
        } else {
            G(i8, i8);
        }
        this.u0 = true;
        return true;
    }

    public final int P() {
        int i = this.r0;
        if (i == 0 || i == 1) {
            return 0;
        }
        return (i == 2 || i == 3) ? 1 : -1;
    }

    @Override // p.ty
    public final void b(ra1 ra1Var, boolean z) {
        boolean z2;
        int i;
        xx[] xxVarArr = this.P;
        xx xxVar = this.H;
        xxVarArr[0] = xxVar;
        xx xxVar2 = this.I;
        int i2 = 2;
        xxVarArr[2] = xxVar2;
        xx xxVar3 = this.J;
        xxVarArr[1] = xxVar3;
        xx xxVar4 = this.K;
        xxVarArr[3] = xxVar4;
        for (xx xxVar5 : xxVarArr) {
            xxVar5.i = ra1Var.k(xxVar5);
        }
        int i3 = this.r0;
        if (i3 < 0 || i3 >= 4) {
            return;
        }
        xx xxVar6 = xxVarArr[i3];
        if (!this.u0) {
            O();
        }
        if (this.u0) {
            this.u0 = false;
            int i4 = this.r0;
            if (i4 == 0 || i4 == 1) {
                ra1Var.d(xxVar.i, this.Y);
                ra1Var.d(xxVar3.i, this.Y);
                return;
            } else {
                if (i4 == 2 || i4 == 3) {
                    ra1Var.d(xxVar2.i, this.Z);
                    ra1Var.d(xxVar4.i, this.Z);
                    return;
                }
                return;
            }
        }
        for (int i5 = 0; i5 < this.q0; i5++) {
            ty tyVar = this.p0[i5];
            if (this.s0 || tyVar.c()) {
                int i6 = this.r0;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.k;
                if (((i6 == 0 || i6 == 1) && tyVar.S[0] == constraintWidget$DimensionBehaviour && tyVar.H.f != null && tyVar.J.f != null) || ((i6 == 2 || i6 == 3) && tyVar.S[1] == constraintWidget$DimensionBehaviour && tyVar.I.f != null && tyVar.K.f != null)) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        boolean z3 = xxVar.e() || xxVar3.e();
        boolean z4 = xxVar2.e() || xxVar4.e();
        int i7 = (z2 || !(((i = this.r0) == 0 && z3) || ((i == 2 && z4) || ((i == 1 && z3) || (i == 3 && z4))))) ? 4 : 5;
        int i8 = 0;
        while (i8 < this.q0) {
            ty tyVar2 = this.p0[i8];
            if (this.s0 || tyVar2.c()) {
                ce2 ce2VarK = ra1Var.k(tyVar2.P[this.r0]);
                int i9 = this.r0;
                xx xxVar7 = tyVar2.P[i9];
                xxVar7.i = ce2VarK;
                xx xxVar8 = xxVar7.f;
                int i10 = (xxVar8 == null || xxVar8.d != this) ? 0 : xxVar7.g;
                if (i9 == 0 || i9 == i2) {
                    ce2 ce2Var = xxVar6.i;
                    int i11 = this.t0 - i10;
                    pb pbVarL = ra1Var.l();
                    ce2 ce2VarM = ra1Var.m();
                    ce2VarM.l = 0;
                    pbVarL.c(ce2Var, ce2VarK, ce2VarM, i11);
                    ra1Var.c(pbVarL);
                } else {
                    ce2 ce2Var2 = xxVar6.i;
                    int i12 = this.t0 + i10;
                    pb pbVarL2 = ra1Var.l();
                    ce2 ce2VarM2 = ra1Var.m();
                    ce2VarM2.l = 0;
                    pbVarL2.b(ce2Var2, ce2VarK, ce2VarM2, i12);
                    ra1Var.c(pbVarL2);
                }
                ra1Var.e(xxVar6.i, ce2VarK, this.t0 + i10, i7);
            }
            i8++;
            i2 = 2;
        }
        int i13 = this.r0;
        if (i13 == 0) {
            ra1Var.e(xxVar3.i, xxVar.i, 0, 8);
            ra1Var.e(xxVar.i, this.T.J.i, 0, 4);
            ra1Var.e(xxVar.i, this.T.H.i, 0, 0);
            return;
        }
        if (i13 == 1) {
            ra1Var.e(xxVar.i, xxVar3.i, 0, 8);
            ra1Var.e(xxVar.i, this.T.H.i, 0, 4);
            ra1Var.e(xxVar.i, this.T.J.i, 0, 0);
        } else if (i13 == 2) {
            ra1Var.e(xxVar4.i, xxVar2.i, 0, 8);
            ra1Var.e(xxVar2.i, this.T.K.i, 0, 4);
            ra1Var.e(xxVar2.i, this.T.I.i, 0, 0);
        } else if (i13 == 3) {
            ra1Var.e(xxVar2.i, xxVar4.i, 0, 8);
            ra1Var.e(xxVar2.i, this.T.I.i, 0, 4);
            ra1Var.e(xxVar2.i, this.T.K.i, 0, 0);
        }
    }

    @Override // p.ty
    public final boolean c() {
        return true;
    }

    @Override // p.ty
    public final String toString() {
        String strM = u4.m(new StringBuilder("[Barrier] "), this.h0, " {");
        for (int i = 0; i < this.q0; i++) {
            ty tyVar = this.p0[i];
            if (i > 0) {
                strM = u4.h(strM, ", ");
            }
            strM = strM + tyVar.h0;
        }
        return u4.h(strM, "}");
    }

    @Override // p.ty
    public final boolean y() {
        return this.u0;
    }

    @Override // p.ty
    public final boolean z() {
        return this.u0;
    }
}
