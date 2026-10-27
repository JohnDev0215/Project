package o1;

import h1.C1342b;
import java.util.ArrayList;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class h extends d {

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public float f20379r0 = -1.0f;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f20380s0 = -1;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f20381t0 = -1;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public c f20382u0 = this.f20266K;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f20383v0 = 0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f20384w0;

    public h() {
        this.f20274S.clear();
        this.f20274S.add(this.f20382u0);
        int length = this.f20273R.length;
        for (int i7 = 0; i7 < length; i7++) {
            this.f20273R[i7] = this.f20382u0;
        }
    }

    @Override // o1.d
    public final boolean B() {
        return this.f20384w0;
    }

    @Override // o1.d
    public final boolean C() {
        return this.f20384w0;
    }

    @Override // o1.d
    public final void U(h1.c cVar, boolean z6) {
        if (this.f20276U == null) {
            return;
        }
        c cVar2 = this.f20382u0;
        cVar.getClass();
        int iN = h1.c.n(cVar2);
        if (this.f20383v0 == 1) {
            this.f20281Z = iN;
            this.f20283a0 = 0;
            N(this.f20276U.l());
            S(0);
            return;
        }
        this.f20281Z = 0;
        this.f20283a0 = iN;
        S(this.f20276U.r());
        N(0);
    }

    public final void V(int i7) {
        this.f20382u0.l(i7);
        this.f20384w0 = true;
    }

    public final void W(int i7) {
        if (this.f20383v0 == i7) {
            return;
        }
        this.f20383v0 = i7;
        ArrayList arrayList = this.f20274S;
        arrayList.clear();
        if (this.f20383v0 == 1) {
            this.f20382u0 = this.f20265J;
        } else {
            this.f20382u0 = this.f20266K;
        }
        arrayList.add(this.f20382u0);
        c[] cVarArr = this.f20273R;
        int length = cVarArr.length;
        for (int i10 = 0; i10 < length; i10++) {
            cVarArr[i10] = this.f20382u0;
        }
    }

    @Override // o1.d
    public final void c(h1.c cVar, boolean z6) {
        e eVar = this.f20276U;
        if (eVar == null) {
            return;
        }
        Object objJ = eVar.j(2);
        Object objJ2 = eVar.j(4);
        e eVar2 = this.f20276U;
        boolean z10 = eVar2 != null && eVar2.f20311q0[0] == 2;
        if (this.f20383v0 == 0) {
            objJ = eVar.j(3);
            objJ2 = eVar.j(5);
            e eVar3 = this.f20276U;
            z10 = eVar3 != null && eVar3.f20311q0[1] == 2;
        }
        if (this.f20384w0) {
            c cVar2 = this.f20382u0;
            if (cVar2.c) {
                h1.e eVarK = cVar.k(cVar2);
                cVar.d(eVarK, this.f20382u0.d());
                if (this.f20380s0 != -1) {
                    if (z10) {
                        cVar.f(cVar.k(objJ2), eVarK, 0, 5);
                    }
                } else if (this.f20381t0 != -1 && z10) {
                    h1.e eVarK2 = cVar.k(objJ2);
                    cVar.f(eVarK, cVar.k(objJ), 0, 5);
                    cVar.f(eVarK2, eVarK, 0, 5);
                }
                this.f20384w0 = false;
                return;
            }
        }
        if (this.f20380s0 != -1) {
            h1.e eVarK3 = cVar.k(this.f20382u0);
            cVar.e(eVarK3, cVar.k(objJ), this.f20380s0, 8);
            if (z10) {
                cVar.f(cVar.k(objJ2), eVarK3, 0, 5);
                return;
            }
            return;
        }
        if (this.f20381t0 != -1) {
            h1.e eVarK4 = cVar.k(this.f20382u0);
            h1.e eVarK5 = cVar.k(objJ2);
            cVar.e(eVarK4, eVarK5, -this.f20381t0, 8);
            if (z10) {
                cVar.f(eVarK4, cVar.k(objJ), 0, 5);
                cVar.f(eVarK5, eVarK4, 0, 5);
                return;
            }
            return;
        }
        if (this.f20379r0 != -1.0f) {
            h1.e eVarK6 = cVar.k(this.f20382u0);
            h1.e eVarK7 = cVar.k(objJ2);
            float f10 = this.f20379r0;
            C1342b c1342bL = cVar.l();
            c1342bL.d.g(eVarK6, -1.0f);
            c1342bL.d.g(eVarK7, f10);
            cVar.c(c1342bL);
        }
    }

    @Override // o1.d
    public final boolean d() {
        return true;
    }

    @Override // o1.d
    public final c j(int i7) {
        int iC = AbstractC2641j.c(i7);
        if (iC != 1) {
            if (iC != 2) {
                if (iC != 3) {
                    if (iC != 4) {
                        return null;
                    }
                }
            }
            if (this.f20383v0 == 0) {
                return this.f20382u0;
            }
            return null;
        }
        if (this.f20383v0 == 1) {
            return this.f20382u0;
        }
        return null;
    }
}
