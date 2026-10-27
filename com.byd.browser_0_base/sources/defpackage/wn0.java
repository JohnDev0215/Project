package defpackage;

import defpackage.qx;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: Flow.java */
/* JADX INFO: loaded from: classes.dex */
public final class wn0 extends lo3 {
    public qx[] g1;
    public int J0 = -1;
    public int K0 = -1;
    public int L0 = -1;
    public int M0 = -1;
    public int N0 = -1;
    public int O0 = -1;
    public float P0 = 0.5f;
    public float Q0 = 0.5f;
    public float R0 = 0.5f;
    public float S0 = 0.5f;
    public float T0 = 0.5f;
    public float U0 = 0.5f;
    public int V0 = 0;
    public int W0 = 0;
    public int X0 = 2;
    public int Y0 = 2;
    public int Z0 = 0;
    public int a1 = -1;
    public int b1 = 0;
    public final ArrayList<a> c1 = new ArrayList<>();
    public qx[] d1 = null;
    public qx[] e1 = null;
    public int[] f1 = null;
    public int h1 = 0;

    /* JADX INFO: compiled from: Flow.java */
    public class a {
        public int a;
        public nx d;
        public nx e;
        public nx f;
        public nx g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int q;
        public qx b = null;
        public int c = 0;
        public int l = 0;
        public int m = 0;
        public int n = 0;
        public int o = 0;
        public int p = 0;

        public a(int i, nx nxVar, nx nxVar2, nx nxVar3, nx nxVar4, int i2) {
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.k = 0;
            this.q = 0;
            this.a = i;
            this.d = nxVar;
            this.e = nxVar2;
            this.f = nxVar3;
            this.g = nxVar4;
            this.h = wn0.this.C0;
            this.i = wn0.this.y0;
            this.j = wn0.this.D0;
            this.k = wn0.this.z0;
            this.q = i2;
        }

        public final void a(qx qxVar) {
            int i = this.a;
            qx.a aVar = qx.a.MATCH_CONSTRAINT;
            wn0 wn0Var = wn0.this;
            if (i == 0) {
                int iY = wn0Var.Y(this.q, qxVar);
                if (qxVar.V[0] == aVar) {
                    this.p++;
                    iY = 0;
                }
                this.l = iY + (qxVar.j0 != 8 ? wn0Var.V0 : 0) + this.l;
                int iX = wn0Var.X(this.q, qxVar);
                if (this.b == null || this.c < iX) {
                    this.b = qxVar;
                    this.c = iX;
                    this.m = iX;
                }
            } else {
                int iY2 = wn0Var.Y(this.q, qxVar);
                int iX2 = wn0Var.X(this.q, qxVar);
                if (qxVar.V[1] == aVar) {
                    this.p++;
                    iX2 = 0;
                }
                this.m = iX2 + (qxVar.j0 != 8 ? wn0Var.W0 : 0) + this.m;
                if (this.b == null || this.c < iY2) {
                    this.b = qxVar;
                    this.c = iY2;
                    this.l = iY2;
                }
            }
            this.o++;
        }

        public final void b(int i, boolean z, boolean z2) {
            wn0 wn0Var;
            int i2;
            int i3;
            int i4;
            qx qxVar;
            int i5;
            char c;
            int i6;
            float f;
            float f2;
            int i7;
            float f3;
            int i8;
            int i9 = this.o;
            int i10 = 0;
            while (true) {
                wn0Var = wn0.this;
                if (i10 >= i9 || (i8 = this.n + i10) >= wn0Var.h1) {
                    break;
                }
                qx qxVar2 = wn0Var.g1[i8];
                if (qxVar2 != null) {
                    qxVar2.H();
                }
                i10++;
            }
            if (i9 == 0 || this.b == null) {
                return;
            }
            boolean z3 = z2 && i == 0;
            int i11 = -1;
            int i12 = -1;
            for (int i13 = 0; i13 < i9; i13++) {
                int i14 = this.n + (z ? (i9 - 1) - i13 : i13);
                if (i14 >= wn0Var.h1) {
                    break;
                }
                qx qxVar3 = wn0Var.g1[i14];
                if (qxVar3 != null && qxVar3.j0 == 0) {
                    if (i11 == -1) {
                        i11 = i13;
                    }
                    i12 = i13;
                }
            }
            if (this.a != 0) {
                qx qxVar4 = this.b;
                qxVar4.n0 = wn0Var.J0;
                int i15 = this.h;
                if (i > 0) {
                    i15 += wn0Var.V0;
                }
                nx nxVar = qxVar4.M;
                nx nxVar2 = qxVar4.K;
                if (z) {
                    nxVar.a(this.f, i15);
                    if (z2) {
                        nxVar2.a(this.d, this.j);
                    }
                    if (i > 0) {
                        this.f.d.K.a(nxVar, 0);
                    }
                } else {
                    nxVar2.a(this.d, i15);
                    if (z2) {
                        nxVar.a(this.f, this.j);
                    }
                    if (i > 0) {
                        this.d.d.M.a(nxVar2, 0);
                    }
                }
                int i16 = 0;
                qx qxVar5 = null;
                while (i16 < i9) {
                    int i17 = this.n + i16;
                    if (i17 >= wn0Var.h1) {
                        return;
                    }
                    qx qxVar6 = wn0Var.g1[i17];
                    if (qxVar6 == null) {
                        qxVar6 = qxVar5;
                    } else {
                        nx nxVar3 = qxVar6.L;
                        if (i16 == 0) {
                            qxVar6.i(nxVar3, this.e, this.i);
                            int i18 = wn0Var.K0;
                            float f4 = wn0Var.Q0;
                            if (this.n == 0) {
                                i4 = wn0Var.M0;
                                i2 = i18;
                                i3 = -1;
                                if (i4 != -1) {
                                    f4 = wn0Var.S0;
                                }
                                qxVar6.o0 = i4;
                                qxVar6.h0 = f4;
                            } else {
                                i2 = i18;
                                i3 = -1;
                            }
                            if (!z2 || (i4 = wn0Var.O0) == i3) {
                                i4 = i2;
                            } else {
                                f4 = wn0Var.U0;
                            }
                            qxVar6.o0 = i4;
                            qxVar6.h0 = f4;
                        }
                        if (i16 == i9 - 1) {
                            qxVar6.i(qxVar6.N, this.g, this.k);
                        }
                        if (qxVar5 != null) {
                            int i19 = wn0Var.W0;
                            nx nxVar4 = qxVar5.N;
                            nxVar3.a(nxVar4, i19);
                            if (i16 == i11) {
                                int i20 = this.i;
                                if (nxVar3.h()) {
                                    nxVar3.h = i20;
                                }
                            }
                            nxVar4.a(nxVar3, 0);
                            if (i16 == i12 + 1) {
                                int i21 = this.k;
                                if (nxVar4.h()) {
                                    nxVar4.h = i21;
                                }
                            }
                        }
                        if (qxVar6 != qxVar4) {
                            nx nxVar5 = qxVar6.M;
                            nx nxVar6 = qxVar6.K;
                            if (z) {
                                int i22 = wn0Var.X0;
                                if (i22 == 0) {
                                    nxVar5.a(nxVar, 0);
                                } else if (i22 == 1) {
                                    nxVar6.a(nxVar2, 0);
                                } else if (i22 == 2) {
                                    nxVar6.a(nxVar2, 0);
                                    nxVar5.a(nxVar, 0);
                                }
                            } else {
                                int i23 = wn0Var.X0;
                                if (i23 == 0) {
                                    nxVar6.a(nxVar2, 0);
                                } else if (i23 == 1) {
                                    nxVar5.a(nxVar, 0);
                                } else if (i23 == 2) {
                                    if (z3) {
                                        nxVar6.a(this.d, this.h);
                                        nxVar5.a(this.f, this.j);
                                    } else {
                                        nxVar6.a(nxVar2, 0);
                                        nxVar5.a(nxVar, 0);
                                    }
                                }
                            }
                        }
                        i16++;
                        qxVar5 = qxVar6;
                    }
                    i16++;
                    qxVar5 = qxVar6;
                }
                return;
            }
            qx qxVar7 = this.b;
            qxVar7.o0 = wn0Var.K0;
            int i24 = this.i;
            if (i > 0) {
                i24 += wn0Var.W0;
            }
            nx nxVar7 = this.e;
            nx nxVar8 = qxVar7.L;
            nxVar8.a(nxVar7, i24);
            nx nxVar9 = qxVar7.N;
            if (z2) {
                nxVar9.a(this.g, this.k);
            }
            if (i > 0) {
                this.e.d.N.a(nxVar8, 0);
            }
            if (wn0Var.Y0 != 3 || qxVar7.F) {
                qxVar = qxVar7;
            } else {
                for (int i25 = 0; i25 < i9; i25++) {
                    int i26 = this.n + (z ? (i9 - 1) - i25 : i25);
                    if (i26 >= wn0Var.h1) {
                        break;
                    }
                    qxVar = wn0Var.g1[i26];
                    if (qxVar.F) {
                        break;
                    }
                }
                qxVar = qxVar7;
            }
            int i27 = 0;
            qx qxVar8 = null;
            while (i27 < i9) {
                int i28 = z ? (i9 - 1) - i27 : i27;
                int i29 = this.n + i28;
                if (i29 >= wn0Var.h1) {
                    return;
                }
                qx qxVar9 = wn0Var.g1[i29];
                if (qxVar9 == null) {
                    i5 = i9;
                    c = 3;
                } else {
                    nx nxVar10 = qxVar9.K;
                    if (i27 == 0) {
                        qxVar9.i(nxVar10, this.d, this.h);
                    }
                    if (i28 == 0) {
                        int i30 = wn0Var.J0;
                        if (z) {
                            i6 = i30;
                            f = 1.0f - wn0Var.P0;
                        } else {
                            i6 = i30;
                            f = wn0Var.P0;
                        }
                        if (this.n == 0) {
                            int i31 = wn0Var.L0;
                            f2 = f;
                            if (i31 != -1) {
                                f3 = z ? 1.0f - wn0Var.R0 : wn0Var.R0;
                                i7 = i31;
                            }
                            qxVar9.n0 = i7;
                            qxVar9.g0 = f3;
                        } else {
                            f2 = f;
                        }
                        if (!z2 || (i7 = wn0Var.N0) == -1) {
                            i7 = i6;
                            f3 = f2;
                        } else {
                            f3 = z ? 1.0f - wn0Var.T0 : wn0Var.T0;
                        }
                        qxVar9.n0 = i7;
                        qxVar9.g0 = f3;
                    }
                    if (i27 == i9 - 1) {
                        i5 = i9;
                        qxVar9.i(qxVar9.M, this.f, this.j);
                    } else {
                        i5 = i9;
                    }
                    if (qxVar8 != null) {
                        int i32 = wn0Var.V0;
                        nx nxVar11 = qxVar8.M;
                        nxVar10.a(nxVar11, i32);
                        if (i27 == i11) {
                            int i33 = this.h;
                            if (nxVar10.h()) {
                                nxVar10.h = i33;
                            }
                        }
                        nxVar11.a(nxVar10, 0);
                        if (i27 == i12 + 1) {
                            int i34 = this.j;
                            if (nxVar11.h()) {
                                nxVar11.h = i34;
                            }
                        }
                    }
                    if (qxVar9 != qxVar7) {
                        int i35 = wn0Var.Y0;
                        c = 3;
                        if (i35 == 3 && qxVar.F && qxVar9 != qxVar && qxVar9.F) {
                            qxVar9.O.a(qxVar.O, 0);
                        } else {
                            nx nxVar12 = qxVar9.L;
                            if (i35 != 0) {
                                nx nxVar13 = qxVar9.N;
                                if (i35 == 1) {
                                    nxVar13.a(nxVar9, 0);
                                } else if (z3) {
                                    nxVar12.a(this.e, this.i);
                                    nxVar13.a(this.g, this.k);
                                } else {
                                    nxVar12.a(nxVar8, 0);
                                    nxVar13.a(nxVar9, 0);
                                }
                            } else {
                                nxVar12.a(nxVar8, 0);
                            }
                        }
                    } else {
                        c = 3;
                    }
                    qxVar8 = qxVar9;
                }
                i27++;
                i9 = i5;
            }
        }

        public final int c() {
            return this.a == 1 ? this.m - wn0.this.W0 : this.m;
        }

        public final int d() {
            return this.a == 0 ? this.l - wn0.this.V0 : this.l;
        }

        public final void e(int i) {
            int i2 = this.p;
            if (i2 == 0) {
                return;
            }
            int i3 = this.o;
            int i4 = i / i2;
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = this.n;
                int i7 = i6 + i5;
                wn0 wn0Var = wn0.this;
                if (i7 >= wn0Var.h1) {
                    break;
                }
                qx qxVar = wn0Var.g1[i6 + i5];
                int i8 = this.a;
                qx.a aVar = qx.a.FIXED;
                qx.a aVar2 = qx.a.MATCH_CONSTRAINT;
                if (i8 == 0) {
                    if (qxVar != null) {
                        qx.a[] aVarArr = qxVar.V;
                        if (aVarArr[0] == aVar2 && qxVar.s == 0) {
                            wn0Var.W(qxVar, aVar, i4, aVarArr[1], qxVar.o());
                        }
                    }
                } else if (qxVar != null) {
                    qx.a[] aVarArr2 = qxVar.V;
                    if (aVarArr2[1] == aVar2 && qxVar.t == 0) {
                        wn0Var.W(qxVar, aVarArr2[0], qxVar.u(), aVar, i4);
                    }
                }
            }
            this.l = 0;
            this.m = 0;
            this.b = null;
            this.c = 0;
            int i9 = this.o;
            for (int i10 = 0; i10 < i9; i10++) {
                int i11 = this.n + i10;
                wn0 wn0Var2 = wn0.this;
                if (i11 >= wn0Var2.h1) {
                    return;
                }
                qx qxVar2 = wn0Var2.g1[i11];
                if (this.a == 0) {
                    int iU = qxVar2.u();
                    int i12 = wn0Var2.V0;
                    if (qxVar2.j0 == 8) {
                        i12 = 0;
                    }
                    this.l = iU + i12 + this.l;
                    int iX = wn0Var2.X(this.q, qxVar2);
                    if (this.b == null || this.c < iX) {
                        this.b = qxVar2;
                        this.c = iX;
                        this.m = iX;
                    }
                } else {
                    int iY = wn0Var2.Y(this.q, qxVar2);
                    int iX2 = wn0Var2.X(this.q, qxVar2);
                    int i13 = wn0Var2.W0;
                    if (qxVar2.j0 == 8) {
                        i13 = 0;
                    }
                    this.m = iX2 + i13 + this.m;
                    if (this.b == null || this.c < iY) {
                        this.b = qxVar2;
                        this.c = iY;
                        this.l = iY;
                    }
                }
            }
        }

        public final void f(int i, nx nxVar, nx nxVar2, nx nxVar3, nx nxVar4, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.d = nxVar;
            this.e = nxVar2;
            this.f = nxVar3;
            this.g = nxVar4;
            this.h = i2;
            this.i = i3;
            this.j = i4;
            this.k = i5;
            this.q = i6;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x082f  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x083e  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0840  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0110  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:293:0x0509 -> B:294:0x0516). Please report as a decompilation issue!!! */
    @Override // defpackage.lo3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void V(int r39, int r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 2146
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wn0.V(int, int, int, int):void");
    }

    public final int X(int i, qx qxVar) {
        if (qxVar == null) {
            return 0;
        }
        if (qxVar.V[1] == qx.a.MATCH_CONSTRAINT) {
            int i2 = qxVar.t;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (qxVar.A * i);
                if (i3 != qxVar.o()) {
                    qxVar.g = true;
                    W(qxVar, qxVar.V[0], qxVar.u(), qx.a.FIXED, i3);
                }
                return i3;
            }
            if (i2 == 1) {
                return qxVar.o();
            }
            if (i2 == 3) {
                return (int) ((qxVar.u() * qxVar.Z) + 0.5f);
            }
        }
        return qxVar.o();
    }

    public final int Y(int i, qx qxVar) {
        if (qxVar == null) {
            return 0;
        }
        if (qxVar.V[0] == qx.a.MATCH_CONSTRAINT) {
            int i2 = qxVar.s;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (qxVar.x * i);
                if (i3 != qxVar.u()) {
                    qxVar.g = true;
                    W(qxVar, qx.a.FIXED, i3, qxVar.V[1], qxVar.o());
                }
                return i3;
            }
            if (i2 == 1) {
                return qxVar.u();
            }
            if (i2 == 3) {
                return (int) ((qxVar.o() * qxVar.Z) + 0.5f);
            }
        }
        return qxVar.u();
    }

    @Override // defpackage.qx
    public final void e(q71 q71Var, boolean z) {
        qx qxVar;
        float f;
        int i;
        super.e(q71Var, z);
        qx qxVar2 = this.W;
        boolean z2 = qxVar2 != null && ((rx) qxVar2).B0;
        int i2 = this.Z0;
        ArrayList<a> arrayList = this.c1;
        if (i2 != 0) {
            if (i2 == 1) {
                int size = arrayList.size();
                int i3 = 0;
                while (i3 < size) {
                    arrayList.get(i3).b(i3, z2, i3 == size + (-1));
                    i3++;
                }
            } else if (i2 != 2) {
                if (i2 == 3) {
                    int size2 = arrayList.size();
                    int i4 = 0;
                    while (i4 < size2) {
                        arrayList.get(i4).b(i4, z2, i4 == size2 + (-1));
                        i4++;
                    }
                }
            } else if (this.f1 != null && this.e1 != null && this.d1 != null) {
                for (int i5 = 0; i5 < this.h1; i5++) {
                    this.g1[i5].H();
                }
                int[] iArr = this.f1;
                int i6 = iArr[0];
                int i7 = iArr[1];
                float f2 = this.P0;
                qx qxVar3 = null;
                int i8 = 0;
                while (i8 < i6) {
                    if (z2) {
                        i = (i6 - i8) - 1;
                        f = 1.0f - this.P0;
                    } else {
                        f = f2;
                        i = i8;
                    }
                    qx qxVar4 = this.e1[i];
                    if (qxVar4 != null && qxVar4.j0 != 8) {
                        nx nxVar = qxVar4.K;
                        if (i8 == 0) {
                            qxVar4.i(nxVar, this.K, this.C0);
                            qxVar4.n0 = this.J0;
                            qxVar4.g0 = f;
                        }
                        if (i8 == i6 - 1) {
                            qxVar4.i(qxVar4.M, this.M, this.D0);
                        }
                        if (i8 > 0 && qxVar3 != null) {
                            int i9 = this.V0;
                            nx nxVar2 = qxVar3.M;
                            qxVar4.i(nxVar, nxVar2, i9);
                            qxVar3.i(nxVar2, nxVar, 0);
                        }
                        qxVar3 = qxVar4;
                    }
                    i8++;
                    f2 = f;
                }
                for (int i10 = 0; i10 < i7; i10++) {
                    qx qxVar5 = this.d1[i10];
                    if (qxVar5 != null && qxVar5.j0 != 8) {
                        nx nxVar3 = qxVar5.L;
                        if (i10 == 0) {
                            qxVar5.i(nxVar3, this.L, this.y0);
                            qxVar5.o0 = this.K0;
                            qxVar5.h0 = this.Q0;
                        }
                        if (i10 == i7 - 1) {
                            qxVar5.i(qxVar5.N, this.N, this.z0);
                        }
                        if (i10 > 0 && qxVar3 != null) {
                            int i11 = this.W0;
                            nx nxVar4 = qxVar3.N;
                            qxVar5.i(nxVar3, nxVar4, i11);
                            qxVar3.i(nxVar4, nxVar3, 0);
                        }
                        qxVar3 = qxVar5;
                    }
                }
                for (int i12 = 0; i12 < i6; i12++) {
                    for (int i13 = 0; i13 < i7; i13++) {
                        int i14 = (i13 * i6) + i12;
                        if (this.b1 == 1) {
                            i14 = (i12 * i7) + i13;
                        }
                        qx[] qxVarArr = this.g1;
                        if (i14 < qxVarArr.length && (qxVar = qxVarArr[i14]) != null && qxVar.j0 != 8) {
                            qx qxVar6 = this.e1[i12];
                            qx qxVar7 = this.d1[i13];
                            if (qxVar != qxVar6) {
                                qxVar.i(qxVar.K, qxVar6.K, 0);
                                qxVar.i(qxVar.M, qxVar6.M, 0);
                            }
                            if (qxVar != qxVar7) {
                                qxVar.i(qxVar.L, qxVar7.L, 0);
                                qxVar.i(qxVar.N, qxVar7.N, 0);
                            }
                        }
                    }
                }
            }
        } else if (arrayList.size() > 0) {
            arrayList.get(0).b(0, z2, true);
        }
        this.E0 = false;
    }

    @Override // defpackage.ku0, defpackage.qx
    public final void j(qx qxVar, HashMap<qx, qx> map) {
        super.j(qxVar, map);
        wn0 wn0Var = (wn0) qxVar;
        this.J0 = wn0Var.J0;
        this.K0 = wn0Var.K0;
        this.L0 = wn0Var.L0;
        this.M0 = wn0Var.M0;
        this.N0 = wn0Var.N0;
        this.O0 = wn0Var.O0;
        this.P0 = wn0Var.P0;
        this.Q0 = wn0Var.Q0;
        this.R0 = wn0Var.R0;
        this.S0 = wn0Var.S0;
        this.T0 = wn0Var.T0;
        this.U0 = wn0Var.U0;
        this.V0 = wn0Var.V0;
        this.W0 = wn0Var.W0;
        this.X0 = wn0Var.X0;
        this.Y0 = wn0Var.Y0;
        this.Z0 = wn0Var.Z0;
        this.a1 = wn0Var.a1;
        this.b1 = wn0Var.b1;
    }
}
