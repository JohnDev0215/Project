package p;

import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d11 extends ty {
    public float p0 = -1.0f;
    public int q0 = -1;
    public int r0 = -1;
    public xx s0 = this.I;
    public int t0 = 0;
    public boolean u0;

    public d11() {
        this.Q.clear();
        this.Q.add(this.s0);
        int length = this.P.length;
        for (int i = 0; i < length; i++) {
            this.P[i] = this.s0;
        }
    }

    @Override // p.ty
    public final void M(ra1 ra1Var, boolean z) {
        if (this.T == null) {
            return;
        }
        xx xxVar = this.s0;
        ra1Var.getClass();
        int iN = ra1.n(xxVar);
        if (this.t0 == 1) {
            this.Y = iN;
            this.Z = 0;
            H(this.T.i());
            K(0);
            return;
        }
        this.Y = 0;
        this.Z = iN;
        K(this.T.o());
        H(0);
    }

    public final void N(int i) {
        this.s0.i(i);
        this.u0 = true;
    }

    public final void O(int i) {
        if (this.t0 == i) {
            return;
        }
        this.t0 = i;
        ArrayList arrayList = this.Q;
        arrayList.clear();
        if (this.t0 == 1) {
            this.s0 = this.H;
        } else {
            this.s0 = this.I;
        }
        arrayList.add(this.s0);
        xx[] xxVarArr = this.P;
        int length = xxVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            xxVarArr[i2] = this.s0;
        }
    }

    @Override // p.ty
    public final void b(ra1 ra1Var, boolean z) {
        uy uyVar = (uy) this.T;
        if (uyVar == null) {
            return;
        }
        Object objG = uyVar.g(ConstraintAnchor$Type.c);
        Object objG2 = uyVar.g(ConstraintAnchor$Type.k);
        ty tyVar = this.T;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.e;
        boolean z2 = tyVar != null && tyVar.S[0] == constraintWidget$DimensionBehaviour;
        if (this.t0 == 0) {
            objG = uyVar.g(ConstraintAnchor$Type.e);
            objG2 = uyVar.g(ConstraintAnchor$Type.l);
            ty tyVar2 = this.T;
            z2 = tyVar2 != null && tyVar2.S[1] == constraintWidget$DimensionBehaviour;
        }
        if (this.u0) {
            xx xxVar = this.s0;
            if (xxVar.c) {
                ce2 ce2VarK = ra1Var.k(xxVar);
                ra1Var.d(ce2VarK, this.s0.c());
                if (this.q0 != -1) {
                    if (z2) {
                        ra1Var.f(ra1Var.k(objG2), ce2VarK, 0, 5);
                    }
                } else if (this.r0 != -1 && z2) {
                    ce2 ce2VarK2 = ra1Var.k(objG2);
                    ra1Var.f(ce2VarK, ra1Var.k(objG), 0, 5);
                    ra1Var.f(ce2VarK2, ce2VarK, 0, 5);
                }
                this.u0 = false;
                return;
            }
        }
        if (this.q0 != -1) {
            ce2 ce2VarK3 = ra1Var.k(this.s0);
            ra1Var.e(ce2VarK3, ra1Var.k(objG), this.q0, 8);
            if (z2) {
                ra1Var.f(ra1Var.k(objG2), ce2VarK3, 0, 5);
                return;
            }
            return;
        }
        if (this.r0 != -1) {
            ce2 ce2VarK4 = ra1Var.k(this.s0);
            ce2 ce2VarK5 = ra1Var.k(objG2);
            ra1Var.e(ce2VarK4, ce2VarK5, -this.r0, 8);
            if (z2) {
                ra1Var.f(ce2VarK4, ra1Var.k(objG), 0, 5);
                ra1Var.f(ce2VarK5, ce2VarK4, 0, 5);
                return;
            }
            return;
        }
        if (this.p0 != -1.0f) {
            ce2 ce2VarK6 = ra1Var.k(this.s0);
            ce2 ce2VarK7 = ra1Var.k(objG2);
            float f = this.p0;
            pb pbVarL = ra1Var.l();
            pbVarL.d.d(ce2VarK6, -1.0f);
            pbVarL.d.d(ce2VarK7, f);
            ra1Var.c(pbVarL);
        }
    }

    @Override // p.ty
    public final boolean c() {
        return true;
    }

    @Override // p.ty
    public final xx g(ConstraintAnchor$Type constraintAnchor$Type) {
        int iOrdinal = constraintAnchor$Type.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        return null;
                    }
                }
            }
            if (this.t0 == 0) {
                return this.s0;
            }
            return null;
        }
        if (this.t0 == 1) {
            return this.s0;
        }
        return null;
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
