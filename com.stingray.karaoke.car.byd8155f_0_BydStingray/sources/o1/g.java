package o1;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class g extends k {

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public int f20355C0;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public int f20356D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public int f20357E0;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public int f20358F0;
    public int G0;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public int f20359H0;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public float f20360I0;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public float f20361J0;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public float f20362K0;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public float f20363L0;

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public float f20364M0;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public float f20365N0;

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public int f20366O0;

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public int f20367P0;

    /* JADX INFO: renamed from: Q0, reason: collision with root package name */
    public int f20368Q0;

    /* JADX INFO: renamed from: R0, reason: collision with root package name */
    public int f20369R0;

    /* JADX INFO: renamed from: S0, reason: collision with root package name */
    public int f20370S0;

    /* JADX INFO: renamed from: T0, reason: collision with root package name */
    public int f20371T0;

    /* JADX INFO: renamed from: U0, reason: collision with root package name */
    public int f20372U0;

    /* JADX INFO: renamed from: V0, reason: collision with root package name */
    public ArrayList f20373V0;

    /* JADX INFO: renamed from: W0, reason: collision with root package name */
    public d[] f20374W0;

    /* JADX INFO: renamed from: X0, reason: collision with root package name */
    public d[] f20375X0;

    /* JADX INFO: renamed from: Y0, reason: collision with root package name */
    public int[] f20376Y0;

    /* JADX INFO: renamed from: Z0, reason: collision with root package name */
    public d[] f20377Z0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public int f20378a1;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:281:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x076d  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0773  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0786  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x07a2  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x07a4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0104  */
    @Override // o1.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void X(int r40, int r41, int r42, int r43) {
        /*
            Method dump skipped, instruction units count: 1960
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.g.X(int, int, int, int):void");
    }

    public final int Z(d dVar, int i7) {
        if (dVar == null) {
            return 0;
        }
        int[] iArr = dVar.f20311q0;
        if (iArr[1] == 3) {
            int i10 = dVar.f20314t;
            if (i10 == 0) {
                return 0;
            }
            if (i10 == 2) {
                int i11 = (int) (dVar.f20257A * i7);
                if (i11 != dVar.l()) {
                    dVar.f20292g = true;
                    Y(iArr[0], dVar.r(), 1, i11, dVar);
                }
                return i11;
            }
            if (i10 == 1) {
                return dVar.l();
            }
            if (i10 == 3) {
                return (int) ((dVar.r() * dVar.f20279X) + 0.5f);
            }
        }
        return dVar.l();
    }

    public final int a0(d dVar, int i7) {
        if (dVar == null) {
            return 0;
        }
        int[] iArr = dVar.f20311q0;
        if (iArr[0] == 3) {
            int i10 = dVar.f20313s;
            if (i10 == 0) {
                return 0;
            }
            if (i10 == 2) {
                int i11 = (int) (dVar.f20318x * i7);
                if (i11 != dVar.r()) {
                    dVar.f20292g = true;
                    Y(1, i11, iArr[1], dVar.l(), dVar);
                }
                return i11;
            }
            if (i10 == 1) {
                return dVar.r();
            }
            if (i10 == 3) {
                return (int) ((dVar.l() * dVar.f20279X) + 0.5f);
            }
        }
        return dVar.r();
    }

    @Override // o1.d
    public final void c(h1.c cVar, boolean z6) {
        d dVar;
        float f10;
        int i7;
        super.c(cVar, z6);
        e eVar = this.f20276U;
        boolean z10 = eVar != null && eVar.f20337w0;
        int i10 = this.f20370S0;
        ArrayList arrayList = this.f20373V0;
        if (i10 != 0) {
            if (i10 == 1) {
                int size = arrayList.size();
                int i11 = 0;
                while (i11 < size) {
                    ((f) arrayList.get(i11)).b(i11, z10, i11 == size + (-1));
                    i11++;
                }
            } else if (i10 != 2) {
                if (i10 == 3) {
                    int size2 = arrayList.size();
                    int i12 = 0;
                    while (i12 < size2) {
                        ((f) arrayList.get(i12)).b(i12, z10, i12 == size2 + (-1));
                        i12++;
                    }
                }
            } else if (this.f20376Y0 != null && this.f20375X0 != null && this.f20374W0 != null) {
                for (int i13 = 0; i13 < this.f20378a1; i13++) {
                    this.f20377Z0[i13].E();
                }
                int[] iArr = this.f20376Y0;
                int i14 = iArr[0];
                int i15 = iArr[1];
                float f11 = this.f20360I0;
                d dVar2 = null;
                int i16 = 0;
                while (i16 < i14) {
                    if (z10) {
                        i7 = (i14 - i16) - 1;
                        f10 = 1.0f - this.f20360I0;
                    } else {
                        f10 = f11;
                        i7 = i16;
                    }
                    d dVar3 = this.f20375X0[i7];
                    if (dVar3 != null && dVar3.f20295h0 != 8) {
                        c cVar2 = dVar3.f20265J;
                        if (i16 == 0) {
                            dVar3.g(cVar2, this.f20265J, this.f20391v0);
                            dVar3.f20299j0 = this.f20355C0;
                            dVar3.f20289e0 = f10;
                        }
                        if (i16 == i14 - 1) {
                            dVar3.g(dVar3.f20267L, this.f20267L, this.f20392w0);
                        }
                        if (i16 > 0 && dVar2 != null) {
                            int i17 = this.f20366O0;
                            c cVar3 = dVar2.f20267L;
                            dVar3.g(cVar2, cVar3, i17);
                            dVar2.g(cVar3, cVar2, 0);
                        }
                        dVar2 = dVar3;
                    }
                    i16++;
                    f11 = f10;
                }
                for (int i18 = 0; i18 < i15; i18++) {
                    d dVar4 = this.f20374W0[i18];
                    if (dVar4 != null && dVar4.f20295h0 != 8) {
                        c cVar4 = dVar4.f20266K;
                        if (i18 == 0) {
                            dVar4.g(cVar4, this.f20266K, this.f20389t0);
                            dVar4.f20301k0 = this.f20356D0;
                            dVar4.f20291f0 = this.f20361J0;
                        }
                        if (i18 == i15 - 1) {
                            dVar4.g(dVar4.f20268M, this.f20268M, this.f20390u0);
                        }
                        if (i18 > 0 && dVar2 != null) {
                            int i19 = this.f20367P0;
                            c cVar5 = dVar2.f20268M;
                            dVar4.g(cVar4, cVar5, i19);
                            dVar2.g(cVar5, cVar4, 0);
                        }
                        dVar2 = dVar4;
                    }
                }
                for (int i20 = 0; i20 < i14; i20++) {
                    for (int i21 = 0; i21 < i15; i21++) {
                        int i22 = (i21 * i14) + i20;
                        if (this.f20372U0 == 1) {
                            i22 = (i20 * i15) + i21;
                        }
                        d[] dVarArr = this.f20377Z0;
                        if (i22 < dVarArr.length && (dVar = dVarArr[i22]) != null && dVar.f20295h0 != 8) {
                            d dVar5 = this.f20375X0[i20];
                            d dVar6 = this.f20374W0[i21];
                            if (dVar != dVar5) {
                                dVar.g(dVar.f20265J, dVar5.f20265J, 0);
                                dVar.g(dVar.f20267L, dVar5.f20267L, 0);
                            }
                            if (dVar != dVar6) {
                                dVar.g(dVar.f20266K, dVar6.f20266K, 0);
                                dVar.g(dVar.f20268M, dVar6.f20268M, 0);
                            }
                        }
                    }
                }
            }
        } else if (arrayList.size() > 0) {
            ((f) arrayList.get(0)).b(0, z10, true);
        }
        this.f20393x0 = false;
    }
}
