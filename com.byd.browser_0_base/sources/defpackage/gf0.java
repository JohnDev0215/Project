package defpackage;

import java.util.HashMap;

/* JADX INFO: compiled from: EarlyHints.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gf0 extends hv2 {
    public static final t50[] e;
    public static final t50 f;
    public vv1 b;
    public int c;
    public int d;

    static {
        t50 t50Var = new t50(24, 0);
        e = new t50[]{t50Var};
        f = t50Var;
    }

    public gf0(int i) {
        super(24);
    }

    public static gf0 d(e70 e70Var) {
        vv1 vv1Var = null;
        if (e70Var == null) {
            return null;
        }
        e70Var.b();
        try {
            gf0 gf0Var = new gf0(e70Var.c(e).b);
            boolean z = false;
            e70 e70VarT = e70Var.t(8, false);
            if (e70VarT != null) {
                e70VarT.b();
                try {
                    vv1 vv1Var2 = new vv1(e70VarT.c(vv1.q).b);
                    e70 e70VarT2 = e70VarT.t(8, false);
                    int i = e70VarT2.k(-1).b;
                    vv1Var2.b = new uy[i];
                    for (int i2 = 0; i2 < i; i2++) {
                        vv1Var2.b[i2] = uy.d(C0173a.g(i2, 8, 8, e70VarT2, false));
                    }
                    vv1Var2.c = n4.b(e70VarT);
                    vv1Var2.d = a20.d(e70VarT.t(32, false));
                    vv1Var2.e = b20.d(e70VarT.t(40, false));
                    int iP = e70VarT.p(48);
                    vv1Var2.f = iP;
                    if (!(iP >= 0 && iP <= 2)) {
                        throw new q80("Invalid enum value.");
                    }
                    vv1Var2.f = iP;
                    int iP2 = e70VarT.p(52);
                    vv1Var2.i = iP2;
                    if (!(iP2 >= 0 && iP2 <= 5)) {
                        throw new q80("Invalid enum value.");
                    }
                    vv1Var2.i = iP2;
                    int[] iArrR = e70VarT.r(56, 1);
                    vv1Var2.g = iArrR;
                    if (iArrR != null) {
                        int i3 = 0;
                        while (true) {
                            int[] iArr = vv1Var2.g;
                            if (i3 >= iArr.length) {
                                break;
                            }
                            a8.m0(iArr[i3]);
                            i3++;
                        }
                    }
                    int[] iArrR2 = e70VarT.r(64, 1);
                    vv1Var2.h = iArrR2;
                    if (iArrR2 != null) {
                        int i4 = 0;
                        while (true) {
                            int[] iArr2 = vv1Var2.h;
                            if (i4 >= iArr2.length) {
                                break;
                            }
                            a8.m0(iArr2[i4]);
                            i4++;
                        }
                    }
                    e70 e70VarT3 = e70VarT.t(72, false);
                    int i5 = e70VarT3.k(-1).b;
                    vv1Var2.j = new r71[i5];
                    for (int i6 = 0; i6 < i5; i6++) {
                        vv1Var2.j[i6] = r71.d(C0173a.g(i6, 8, 8, e70VarT3, false));
                    }
                    vv1Var2.k = wa3.b(e70VarT);
                    vv1Var2.l = e70VarT.r(96, 0);
                    int i7 = 0;
                    while (true) {
                        int[] iArr3 = vv1Var2.l;
                        if (i7 < iArr3.length) {
                            int i8 = iArr3[i7];
                            if (!(i8 >= 0 && i8 <= 4)) {
                                throw new q80("Invalid enum value.");
                            }
                            i7++;
                        } else {
                            e70 e70VarT4 = e70VarT.t(104, true);
                            if (e70VarT4 == null) {
                                vv1Var2.m = null;
                            } else {
                                e70VarT4.j();
                                e70 e70VarT5 = e70VarT4.t(8, false);
                                int i9 = e70VarT5.k(-1).b;
                                String[] strArr = new String[i9];
                                for (int i10 = 0; i10 < i9; i10++) {
                                    strArr[i10] = C0175b.g(i10, 8, 8, e70VarT5, false);
                                }
                                e70 e70VarT6 = e70VarT4.t(16, false);
                                int i11 = e70VarT6.k(i9).b;
                                String[] strArr2 = new String[i11];
                                for (int i12 = 0; i12 < i11; i12++) {
                                    strArr2[i12] = C0175b.g(i12, 8, 8, e70VarT6, false);
                                }
                                vv1Var2.m = new HashMap();
                                for (int i13 = 0; i13 < i9; i13++) {
                                    vv1Var2.m.put(strArr[i13], strArr2[i13]);
                                }
                            }
                            e70 e70VarT7 = e70VarT.t(112, true);
                            if (e70VarT7 == null) {
                                vv1Var2.n = null;
                            } else {
                                int i14 = e70VarT7.k(-1).b;
                                vv1Var2.n = new xk3[i14];
                                for (int i15 = 0; i15 < i14; i15++) {
                                    vv1Var2.n[i15] = xk3.d(C0173a.g(i15, 8, 8, e70VarT7, false));
                                }
                            }
                            e70 e70VarT8 = e70VarT.t(120, true);
                            if (e70VarT8 == null) {
                                vv1Var2.o = null;
                            } else {
                                int i16 = e70VarT8.k(-1).b;
                                vv1Var2.o = new String[i16];
                                for (int i17 = 0; i17 < i16; i17++) {
                                    vv1Var2.o[i17] = C0175b.g(i17, 8, 8, e70VarT8, false);
                                }
                            }
                            vv1Var2.p = wn1.b(e70VarT);
                            e70VarT.a();
                            vv1Var = vv1Var2;
                        }
                    }
                } catch (Throwable th) {
                    e70VarT.a();
                    throw th;
                }
            }
            gf0Var.b = vv1Var;
            int iP3 = e70Var.p(16);
            gf0Var.c = iP3;
            if (iP3 >= 0 && iP3 <= 8) {
                z = true;
            }
            gf0Var.c = z ? iP3 : 1;
            int iP4 = e70Var.p(20);
            gf0Var.d = iP4;
            n3.v1(iP4);
            gf0Var.d = gf0Var.d;
            return gf0Var;
        } finally {
            e70Var.a();
        }
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(f);
        qh0VarT.k(this.b, 8, false);
        qh0VarT.b(this.c, 16);
        qh0VarT.b(this.d, 20);
    }
}
