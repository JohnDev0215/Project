package o1;

import h1.C1342b;

/* JADX INFO: loaded from: classes.dex */
public final class a extends i {

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f20233t0 = 0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public boolean f20234u0 = true;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f20235v0 = 0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f20236w0 = false;

    @Override // o1.d
    public final boolean B() {
        return this.f20236w0;
    }

    @Override // o1.d
    public final boolean C() {
        return this.f20236w0;
    }

    public final boolean X() {
        int i7;
        int i10;
        int i11;
        boolean z6 = true;
        int i12 = 0;
        while (true) {
            i7 = this.f20386s0;
            if (i12 >= i7) {
                break;
            }
            d dVar = this.f20385r0[i12];
            if ((this.f20234u0 || dVar.d()) && ((((i10 = this.f20233t0) == 0 || i10 == 1) && !dVar.B()) || (((i11 = this.f20233t0) == 2 || i11 == 3) && !dVar.C()))) {
                z6 = false;
            }
            i12++;
        }
        if (!z6 || i7 <= 0) {
            return false;
        }
        int iMax = 0;
        boolean z10 = false;
        for (int i13 = 0; i13 < this.f20386s0; i13++) {
            d dVar2 = this.f20385r0[i13];
            if (this.f20234u0 || dVar2.d()) {
                if (!z10) {
                    int i14 = this.f20233t0;
                    if (i14 == 0) {
                        iMax = dVar2.j(2).d();
                    } else if (i14 == 1) {
                        iMax = dVar2.j(4).d();
                    } else if (i14 == 2) {
                        iMax = dVar2.j(3).d();
                    } else if (i14 == 3) {
                        iMax = dVar2.j(5).d();
                    }
                    z10 = true;
                }
                int i15 = this.f20233t0;
                if (i15 == 0) {
                    iMax = Math.min(iMax, dVar2.j(2).d());
                } else if (i15 == 1) {
                    iMax = Math.max(iMax, dVar2.j(4).d());
                } else if (i15 == 2) {
                    iMax = Math.min(iMax, dVar2.j(3).d());
                } else if (i15 == 3) {
                    iMax = Math.max(iMax, dVar2.j(5).d());
                }
            }
        }
        int i16 = iMax + this.f20235v0;
        int i17 = this.f20233t0;
        if (i17 == 0 || i17 == 1) {
            L(i16, i16);
        } else {
            M(i16, i16);
        }
        this.f20236w0 = true;
        return true;
    }

    public final int Y() {
        int i7 = this.f20233t0;
        if (i7 == 0 || i7 == 1) {
            return 0;
        }
        return (i7 == 2 || i7 == 3) ? 1 : -1;
    }

    @Override // o1.d
    public final void c(h1.c cVar, boolean z6) {
        boolean z10;
        int i7;
        int i10;
        c[] cVarArr = this.f20273R;
        c cVar2 = this.f20265J;
        cVarArr[0] = cVar2;
        c cVar3 = this.f20266K;
        int i11 = 2;
        cVarArr[2] = cVar3;
        c cVar4 = this.f20267L;
        cVarArr[1] = cVar4;
        c cVar5 = this.f20268M;
        cVarArr[3] = cVar5;
        for (c cVar6 : cVarArr) {
            cVar6.f20256i = cVar.k(cVar6);
        }
        int i12 = this.f20233t0;
        if (i12 < 0 || i12 >= 4) {
            return;
        }
        c cVar7 = cVarArr[i12];
        if (!this.f20236w0) {
            X();
        }
        if (this.f20236w0) {
            this.f20236w0 = false;
            int i13 = this.f20233t0;
            if (i13 == 0 || i13 == 1) {
                cVar.d(cVar2.f20256i, this.f20281Z);
                cVar.d(cVar4.f20256i, this.f20281Z);
                return;
            } else {
                if (i13 == 2 || i13 == 3) {
                    cVar.d(cVar3.f20256i, this.f20283a0);
                    cVar.d(cVar5.f20256i, this.f20283a0);
                    return;
                }
                return;
            }
        }
        for (int i14 = 0; i14 < this.f20386s0; i14++) {
            d dVar = this.f20385r0[i14];
            if ((this.f20234u0 || dVar.d()) && ((((i10 = this.f20233t0) == 0 || i10 == 1) && dVar.f20311q0[0] == 3 && dVar.f20265J.f20253f != null && dVar.f20267L.f20253f != null) || ((i10 == 2 || i10 == 3) && dVar.f20311q0[1] == 3 && dVar.f20266K.f20253f != null && dVar.f20268M.f20253f != null))) {
                z10 = true;
                break;
            }
        }
        z10 = false;
        boolean z11 = cVar2.g() || cVar4.g();
        boolean z12 = cVar3.g() || cVar5.g();
        int i15 = (z10 || !(((i7 = this.f20233t0) == 0 && z11) || ((i7 == 2 && z12) || ((i7 == 1 && z11) || (i7 == 3 && z12))))) ? 4 : 5;
        int i16 = 0;
        while (i16 < this.f20386s0) {
            d dVar2 = this.f20385r0[i16];
            if (this.f20234u0 || dVar2.d()) {
                h1.e eVarK = cVar.k(dVar2.f20273R[this.f20233t0]);
                int i17 = this.f20233t0;
                c cVar8 = dVar2.f20273R[i17];
                cVar8.f20256i = eVarK;
                c cVar9 = cVar8.f20253f;
                int i18 = (cVar9 == null || cVar9.d != this) ? 0 : cVar8.f20254g;
                if (i17 == 0 || i17 == i11) {
                    h1.e eVar = cVar7.f20256i;
                    int i19 = this.f20235v0 - i18;
                    C1342b c1342bL = cVar.l();
                    h1.e eVarM = cVar.m();
                    eVarM.o = 0;
                    c1342bL.c(eVar, eVarK, eVarM, i19);
                    cVar.c(c1342bL);
                } else {
                    h1.e eVar2 = cVar7.f20256i;
                    int i20 = this.f20235v0 + i18;
                    C1342b c1342bL2 = cVar.l();
                    h1.e eVarM2 = cVar.m();
                    eVarM2.o = 0;
                    c1342bL2.b(eVar2, eVarK, eVarM2, i20);
                    cVar.c(c1342bL2);
                }
                cVar.e(cVar7.f20256i, eVarK, this.f20235v0 + i18, i15);
            }
            i16++;
            i11 = 2;
        }
        int i21 = this.f20233t0;
        if (i21 == 0) {
            cVar.e(cVar4.f20256i, cVar2.f20256i, 0, 8);
            cVar.e(cVar2.f20256i, this.f20276U.f20267L.f20256i, 0, 4);
            cVar.e(cVar2.f20256i, this.f20276U.f20265J.f20256i, 0, 0);
            return;
        }
        if (i21 == 1) {
            cVar.e(cVar2.f20256i, cVar4.f20256i, 0, 8);
            cVar.e(cVar2.f20256i, this.f20276U.f20265J.f20256i, 0, 4);
            cVar.e(cVar2.f20256i, this.f20276U.f20267L.f20256i, 0, 0);
        } else if (i21 == 2) {
            cVar.e(cVar5.f20256i, cVar3.f20256i, 0, 8);
            cVar.e(cVar3.f20256i, this.f20276U.f20268M.f20256i, 0, 4);
            cVar.e(cVar3.f20256i, this.f20276U.f20266K.f20256i, 0, 0);
        } else if (i21 == 3) {
            cVar.e(cVar3.f20256i, cVar5.f20256i, 0, 8);
            cVar.e(cVar3.f20256i, this.f20276U.f20266K.f20256i, 0, 4);
            cVar.e(cVar3.f20256i, this.f20276U.f20268M.f20256i, 0, 0);
        }
    }

    @Override // o1.d
    public final boolean d() {
        return true;
    }

    @Override // o1.d
    public final String toString() {
        String strL = android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("[Barrier] "), this.f20297i0, " {");
        for (int i7 = 0; i7 < this.f20386s0; i7++) {
            d dVar = this.f20385r0[i7];
            if (i7 > 0) {
                strL = android.hardware.bydauto.ac.tsimpl.a.h(strL, ", ");
            }
            StringBuilder sbI = M5.b.i(strL);
            sbI.append(dVar.f20297i0);
            strL = sbI.toString();
        }
        return android.hardware.bydauto.ac.tsimpl.a.h(strL, "}");
    }
}
