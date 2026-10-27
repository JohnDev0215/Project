package defpackage;

import defpackage.nx;
import defpackage.qx;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Guideline.java */
/* JADX INFO: loaded from: classes.dex */
public final class rt0 extends qx {
    public boolean B0;
    public float w0 = -1.0f;
    public int x0 = -1;
    public int y0 = -1;
    public nx z0 = this.L;
    public int A0 = 0;

    public rt0() {
        this.T.clear();
        this.T.add(this.z0);
        int length = this.S.length;
        for (int i = 0; i < length; i++) {
            this.S[i] = this.z0;
        }
    }

    @Override // defpackage.qx
    public final boolean E() {
        return this.B0;
    }

    @Override // defpackage.qx
    public final boolean F() {
        return this.B0;
    }

    @Override // defpackage.qx
    public final void T(q71 q71Var, boolean z) {
        if (this.W == null) {
            return;
        }
        nx nxVar = this.z0;
        q71Var.getClass();
        int iN = q71.n(nxVar);
        if (this.A0 == 1) {
            this.b0 = iN;
            this.c0 = 0;
            O(this.W.o());
            R(0);
            return;
        }
        this.b0 = 0;
        this.c0 = iN;
        R(this.W.u());
        O(0);
    }

    public final void U(int i) {
        this.z0.l(i);
        this.B0 = true;
    }

    public final void V(int i) {
        if (this.A0 == i) {
            return;
        }
        this.A0 = i;
        ArrayList<nx> arrayList = this.T;
        arrayList.clear();
        if (this.A0 == 1) {
            this.z0 = this.K;
        } else {
            this.z0 = this.L;
        }
        arrayList.add(this.z0);
        nx[] nxVarArr = this.S;
        int length = nxVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            nxVarArr[i2] = this.z0;
        }
    }

    @Override // defpackage.qx
    public final void e(q71 q71Var, boolean z) {
        rx rxVar = (rx) this.W;
        if (rxVar == null) {
            return;
        }
        Object objM = rxVar.m(nx.a.LEFT);
        Object objM2 = rxVar.m(nx.a.RIGHT);
        qx qxVar = this.W;
        qx.a aVar = qx.a.WRAP_CONTENT;
        boolean z2 = qxVar != null && qxVar.V[0] == aVar;
        if (this.A0 == 0) {
            objM = rxVar.m(nx.a.TOP);
            objM2 = rxVar.m(nx.a.BOTTOM);
            qx qxVar2 = this.W;
            z2 = qxVar2 != null && qxVar2.V[1] == aVar;
        }
        if (this.B0) {
            nx nxVar = this.z0;
            if (nxVar.c) {
                hq2 hq2VarK = q71Var.k(nxVar);
                q71Var.d(hq2VarK, this.z0.d());
                if (this.x0 != -1) {
                    if (z2) {
                        q71Var.f(q71Var.k(objM2), hq2VarK, 0, 5);
                    }
                } else if (this.y0 != -1 && z2) {
                    hq2 hq2VarK2 = q71Var.k(objM2);
                    q71Var.f(hq2VarK, q71Var.k(objM), 0, 5);
                    q71Var.f(hq2VarK2, hq2VarK, 0, 5);
                }
                this.B0 = false;
                return;
            }
        }
        if (this.x0 != -1) {
            hq2 hq2VarK3 = q71Var.k(this.z0);
            q71Var.e(hq2VarK3, q71Var.k(objM), this.x0, 8);
            if (z2) {
                q71Var.f(q71Var.k(objM2), hq2VarK3, 0, 5);
                return;
            }
            return;
        }
        if (this.y0 != -1) {
            hq2 hq2VarK4 = q71Var.k(this.z0);
            hq2 hq2VarK5 = q71Var.k(objM2);
            q71Var.e(hq2VarK4, hq2VarK5, -this.y0, 8);
            if (z2) {
                q71Var.f(hq2VarK4, q71Var.k(objM), 0, 5);
                q71Var.f(hq2VarK5, hq2VarK4, 0, 5);
                return;
            }
            return;
        }
        if (this.w0 != -1.0f) {
            hq2 hq2VarK6 = q71Var.k(this.z0);
            hq2 hq2VarK7 = q71Var.k(objM2);
            float f = this.w0;
            p8 p8VarL = q71Var.l();
            p8VarL.d.k(hq2VarK6, -1.0f);
            p8VarL.d.k(hq2VarK7, f);
            q71Var.c(p8VarL);
        }
    }

    @Override // defpackage.qx
    public final boolean f() {
        return true;
    }

    @Override // defpackage.qx
    public final void j(qx qxVar, HashMap<qx, qx> map) {
        super.j(qxVar, map);
        rt0 rt0Var = (rt0) qxVar;
        this.w0 = rt0Var.w0;
        this.x0 = rt0Var.x0;
        this.y0 = rt0Var.y0;
        V(rt0Var.A0);
    }

    @Override // defpackage.qx
    public final nx m(nx.a aVar) {
        int iOrdinal = aVar.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        return null;
                    }
                }
            }
            if (this.A0 == 0) {
                return this.z0;
            }
            return null;
        }
        if (this.A0 == 1) {
            return this.z0;
        }
        return null;
    }
}
