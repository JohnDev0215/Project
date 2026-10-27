package x;

import h2.AbstractC1347d;
import u.C2475o;
import u.C2476p;
import w8.C2607b;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class K0 implements G0 {
    public final C2475o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2476p f23424m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f23425n;
    public final u2.I o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int[] f23426p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float[] f23427q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AbstractC2652s f23428r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC2652s f23429s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AbstractC2652s f23430t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public AbstractC2652s f23431u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float[] f23432v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float[] f23433w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C2607b f23434x;

    public K0(C2475o c2475o, C2476p c2476p, int i7, u2.I i10) {
        this.l = c2475o;
        this.f23424m = c2476p;
        this.f23425n = i7;
        this.o = i10;
    }

    @Override // x.F0
    public final AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        int i7 = 0;
        long jW = AbstractC2673a.w((j3 / 1000000) - ((long) 0), 0L, this.f23425n);
        if (jW < 0) {
            return abstractC2652s3;
        }
        f(abstractC2652s, abstractC2652s2, abstractC2652s3);
        if (this.f23434x == null) {
            AbstractC2652s abstractC2652sO = o((jW - 1) * 1000000, abstractC2652s, abstractC2652s2, abstractC2652s3);
            AbstractC2652s abstractC2652sO2 = o(jW * 1000000, abstractC2652s, abstractC2652s2, abstractC2652s3);
            int iB = abstractC2652sO.b();
            while (i7 < iB) {
                AbstractC2652s abstractC2652s4 = this.f23429s;
                if (abstractC2652s4 == null) {
                    A7.m.j("velocityVector");
                    throw null;
                }
                abstractC2652s4.e((abstractC2652sO.a(i7) - abstractC2652sO2.a(i7)) * 1000.0f, i7);
                i7++;
            }
            AbstractC2652s abstractC2652s5 = this.f23429s;
            if (abstractC2652s5 != null) {
                return abstractC2652s5;
            }
            A7.m.j("velocityVector");
            throw null;
        }
        int i10 = (int) jW;
        float fE = e(d(i10), i10, false);
        C2607b c2607b = this.f23434x;
        if (c2607b == null) {
            A7.m.j("arcSpline");
            throw null;
        }
        float[] fArr = this.f23433w;
        if (fArr == null) {
            A7.m.j("slopeArray");
            throw null;
        }
        C2654u[][] c2654uArr = (C2654u[][]) c2607b.f23345m;
        float f10 = c2654uArr[0][0].f23618a;
        if (fE < f10) {
            fE = f10;
        } else if (fE > c2654uArr[c2654uArr.length - 1][0].f23619b) {
            fE = c2654uArr[c2654uArr.length - 1][0].f23619b;
        }
        int length = c2654uArr.length;
        boolean z6 = false;
        for (int i11 = 0; i11 < length; i11++) {
            int i12 = 0;
            int i13 = 0;
            while (i12 < fArr.length) {
                C2654u c2654u = c2654uArr[i11][i13];
                if (fE <= c2654u.f23619b) {
                    if (c2654u.f23631r) {
                        fArr[i12] = c2654u.f23628n;
                        fArr[i12 + 1] = c2654u.o;
                    } else {
                        c2654u.c(fE);
                        fArr[i12] = c2654uArr[i11][i13].a();
                        fArr[i12 + 1] = c2654uArr[i11][i13].b();
                    }
                    z6 = true;
                }
                i12 += 2;
                i13++;
            }
            if (z6) {
                break;
            }
        }
        float[] fArr2 = this.f23433w;
        if (fArr2 == null) {
            A7.m.j("slopeArray");
            throw null;
        }
        int length2 = fArr2.length;
        while (i7 < length2) {
            AbstractC2652s abstractC2652s6 = this.f23429s;
            if (abstractC2652s6 == null) {
                A7.m.j("velocityVector");
                throw null;
            }
            float[] fArr3 = this.f23433w;
            if (fArr3 == null) {
                A7.m.j("slopeArray");
                throw null;
            }
            abstractC2652s6.e(fArr3[i7], i7);
            i7++;
        }
        AbstractC2652s abstractC2652s7 = this.f23429s;
        if (abstractC2652s7 != null) {
            return abstractC2652s7;
        }
        A7.m.j("velocityVector");
        throw null;
    }

    public final int d(int i7) {
        int i10;
        C2475o c2475o = this.l;
        int i11 = c2475o.f22614b;
        if (i11 < 0) {
            throw new IllegalArgumentException("fromIndex(0) > toIndex(" + i11 + ')');
        }
        int i12 = i11 - 1;
        int i13 = 0;
        while (true) {
            if (i13 <= i12) {
                i10 = (i13 + i12) >>> 1;
                int iC = c2475o.c(i10);
                if (iC >= i7) {
                    if (iC <= i7) {
                        break;
                    }
                    i12 = i10 - 1;
                } else {
                    i13 = i10 + 1;
                }
            } else {
                i10 = -(i13 + 1);
                break;
            }
        }
        return i10 < -1 ? -(i10 + 2) : i10;
    }

    public final float e(int i7, int i10, boolean z6) {
        InterfaceC2612A interfaceC2612A;
        float f10;
        C2475o c2475o = this.l;
        if (i7 >= c2475o.f22614b - 1) {
            f10 = i10;
        } else {
            int iC = c2475o.c(i7);
            int iC2 = c2475o.c(i7 + 1);
            if (i10 != iC) {
                int i11 = iC2 - iC;
                J0 j02 = (J0) this.f23424m.f(iC);
                if (j02 == null || (interfaceC2612A = j02.f23420b) == null) {
                    interfaceC2612A = this.o;
                }
                float f11 = i11;
                float fA = interfaceC2612A.a((i10 - iC) / f11);
                return z6 ? fA : ((f11 * fA) + iC) / 1000;
            }
            f10 = iC;
        }
        return f10 / 1000;
    }

    public final void f(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        float[] fArr;
        float[] fArr2;
        boolean z6 = this.f23434x != null;
        AbstractC2652s abstractC2652s4 = this.f23428r;
        C2476p c2476p = this.f23424m;
        C2475o c2475o = this.l;
        if (abstractC2652s4 == null) {
            this.f23428r = abstractC2652s.c();
            this.f23429s = abstractC2652s3.c();
            int i7 = c2475o.f22614b;
            float[] fArr3 = new float[i7];
            for (int i10 = 0; i10 < i7; i10++) {
                fArr3[i10] = c2475o.c(i10) / 1000;
            }
            this.f23427q = fArr3;
            int i11 = c2475o.f22614b;
            int[] iArr = new int[i11];
            for (int i12 = 0; i12 < i11; i12++) {
                J0 j02 = (J0) c2476p.f(c2475o.c(i12));
                int i13 = j02 != null ? j02.c : 0;
                if (i13 != 0) {
                    z6 = true;
                }
                iArr[i12] = i13;
            }
            this.f23426p = iArr;
        }
        if (z6) {
            if (this.f23434x != null) {
                AbstractC2652s abstractC2652s5 = this.f23430t;
                if (abstractC2652s5 == null) {
                    A7.m.j("lastInitialValue");
                    throw null;
                }
                if (abstractC2652s5.equals(abstractC2652s)) {
                    AbstractC2652s abstractC2652s6 = this.f23431u;
                    if (abstractC2652s6 == null) {
                        A7.m.j("lastTargetValue");
                        throw null;
                    }
                    if (abstractC2652s6.equals(abstractC2652s2)) {
                        return;
                    }
                }
            }
            this.f23430t = abstractC2652s;
            this.f23431u = abstractC2652s2;
            int iB = abstractC2652s.b() + (abstractC2652s.b() % 2);
            this.f23432v = new float[iB];
            this.f23433w = new float[iB];
            int i14 = c2475o.f22614b;
            float[][] fArr4 = new float[i14][];
            for (int i15 = 0; i15 < i14; i15++) {
                int iC = c2475o.c(i15);
                if (iC != 0) {
                    if (iC != this.f23425n) {
                        fArr = new float[iB];
                        Object objF = c2476p.f(iC);
                        A7.m.c(objF);
                        J0 j03 = (J0) objF;
                        for (int i16 = 0; i16 < iB; i16++) {
                            fArr[i16] = j03.f23419a.a(i16);
                        }
                    } else if (c2476p.b(iC)) {
                        fArr = new float[iB];
                        Object objF2 = c2476p.f(iC);
                        A7.m.c(objF2);
                        J0 j04 = (J0) objF2;
                        for (int i17 = 0; i17 < iB; i17++) {
                            fArr[i17] = j04.f23419a.a(i17);
                        }
                    } else {
                        fArr2 = new float[iB];
                        for (int i18 = 0; i18 < iB; i18++) {
                            fArr2[i18] = abstractC2652s2.a(i18);
                        }
                    }
                    fArr2 = fArr;
                } else if (c2476p.b(iC)) {
                    fArr = new float[iB];
                    Object objF3 = c2476p.f(iC);
                    A7.m.c(objF3);
                    J0 j05 = (J0) objF3;
                    for (int i19 = 0; i19 < iB; i19++) {
                        fArr[i19] = j05.f23419a.a(i19);
                    }
                    fArr2 = fArr;
                } else {
                    fArr2 = new float[iB];
                    for (int i20 = 0; i20 < iB; i20++) {
                        fArr2[i20] = abstractC2652s.a(i20);
                    }
                }
                fArr4[i15] = fArr2;
            }
            int[] iArr2 = this.f23426p;
            if (iArr2 == null) {
                A7.m.j("modes");
                throw null;
            }
            float[] fArr5 = this.f23427q;
            if (fArr5 == null) {
                A7.m.j("times");
                throw null;
            }
            this.f23434x = new C2607b(iArr2, fArr5, fArr4);
        }
    }

    @Override // x.G0
    public final int n() {
        return 0;
    }

    @Override // x.F0
    public final AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        int length;
        AbstractC2652s abstractC2652s4 = abstractC2652s;
        AbstractC2652s abstractC2652s5 = abstractC2652s2;
        int i7 = 1;
        int i10 = 0;
        int i11 = this.f23425n;
        int iW = (int) AbstractC2673a.w((j3 / 1000000) - ((long) 0), 0L, i11);
        C2476p c2476p = this.f23424m;
        if (c2476p.b(iW)) {
            Object objF = c2476p.f(iW);
            A7.m.c(objF);
            return ((J0) objF).f23419a;
        }
        if (iW >= i11) {
            return abstractC2652s5;
        }
        if (iW <= 0) {
            return abstractC2652s4;
        }
        f(abstractC2652s4, abstractC2652s5, abstractC2652s3);
        if (this.f23434x == null) {
            int iD = d(iW);
            float fE = e(iD, iW, true);
            C2475o c2475o = this.l;
            int iC = c2475o.c(iD);
            if (c2476p.b(iC)) {
                Object objF2 = c2476p.f(iC);
                A7.m.c(objF2);
                abstractC2652s4 = ((J0) objF2).f23419a;
            }
            int iC2 = c2475o.c(iD + 1);
            if (c2476p.b(iC2)) {
                Object objF3 = c2476p.f(iC2);
                A7.m.c(objF3);
                abstractC2652s5 = ((J0) objF3).f23419a;
            }
            AbstractC2652s abstractC2652s6 = this.f23428r;
            if (abstractC2652s6 == null) {
                A7.m.j("valueVector");
                throw null;
            }
            int iB = abstractC2652s6.b();
            for (int i12 = 0; i12 < iB; i12++) {
                AbstractC2652s abstractC2652s7 = this.f23428r;
                if (abstractC2652s7 == null) {
                    A7.m.j("valueVector");
                    throw null;
                }
                float fA = abstractC2652s4.a(i12);
                float fA2 = abstractC2652s5.a(i12);
                D0 d02 = E0.f23396a;
                abstractC2652s7.e((fA2 * fE) + ((1 - fE) * fA), i12);
            }
            AbstractC2652s abstractC2652s8 = this.f23428r;
            if (abstractC2652s8 != null) {
                return abstractC2652s8;
            }
            A7.m.j("valueVector");
            throw null;
        }
        float fE2 = e(d(iW), iW, false);
        C2607b c2607b = this.f23434x;
        if (c2607b == null) {
            A7.m.j("arcSpline");
            throw null;
        }
        float[] fArr = this.f23432v;
        if (fArr == null) {
            A7.m.j("posArray");
            throw null;
        }
        C2654u[][] c2654uArr = (C2654u[][]) c2607b.f23345m;
        float f10 = c2654uArr[0][0].f23618a;
        if (fE2 < f10 || fE2 > c2654uArr[c2654uArr.length - 1][0].f23619b) {
            if (fE2 > c2654uArr[c2654uArr.length - 1][0].f23619b) {
                length = c2654uArr.length - 1;
                f10 = c2654uArr[c2654uArr.length - 1][0].f23619b;
            } else {
                length = 0;
            }
            float f11 = fE2 - f10;
            int i13 = 0;
            int i14 = 0;
            while (i13 < fArr.length) {
                C2654u c2654u = c2654uArr[length][i14];
                if (c2654u.f23631r) {
                    float f12 = c2654u.f23618a;
                    float f13 = c2654u.f23626k;
                    float f14 = c2654u.f23620e;
                    float f15 = c2654u.c;
                    fArr[i13] = (c2654u.f23628n * f11) + AbstractC1347d.a(f14, f15, (f10 - f12) * f13, f15);
                    float f16 = (f10 - f12) * f13;
                    float f17 = c2654u.f23621f;
                    float f18 = c2654u.d;
                    fArr[i13 + 1] = (c2654u.o * f11) + AbstractC1347d.a(f17, f18, f16, f18);
                } else {
                    c2654u.c(f10);
                    C2654u c2654u2 = c2654uArr[length][i14];
                    fArr[i13] = (c2654u2.a() * f11) + (c2654u2.l * c2654u2.f23623h) + c2654u2.f23628n;
                    C2654u c2654u3 = c2654uArr[length][i14];
                    fArr[i13 + 1] = (c2654u3.b() * f11) + (c2654u3.f23627m * c2654u3.f23624i) + c2654u3.o;
                }
                i13 += 2;
                i14++;
            }
        } else {
            int length2 = c2654uArr.length;
            int i15 = 0;
            boolean z6 = false;
            while (i15 < length2) {
                int i16 = i10;
                int i17 = i16;
                while (i16 < fArr.length) {
                    C2654u c2654u4 = c2654uArr[i15][i17];
                    if (fE2 <= c2654u4.f23619b) {
                        if (c2654u4.f23631r) {
                            float f19 = c2654u4.f23618a;
                            float f20 = c2654u4.f23626k;
                            float f21 = c2654u4.f23620e;
                            float f22 = c2654u4.c;
                            fArr[i16] = AbstractC1347d.a(f21, f22, (fE2 - f19) * f20, f22);
                            float f23 = (fE2 - f19) * f20;
                            float f24 = c2654u4.f23621f;
                            float f25 = c2654u4.d;
                            fArr[i16 + 1] = AbstractC1347d.a(f24, f25, f23, f25);
                        } else {
                            c2654u4.c(fE2);
                            C2654u c2654u5 = c2654uArr[i15][i17];
                            fArr[i16] = (c2654u5.l * c2654u5.f23623h) + c2654u5.f23628n;
                            fArr[i16 + 1] = (c2654u5.f23627m * c2654u5.f23624i) + c2654u5.o;
                        }
                        z6 = true;
                    }
                    i16 += 2;
                    i7 = 1;
                    i17++;
                }
                if (z6) {
                    break;
                }
                i15 += i7;
                i10 = 0;
            }
        }
        float[] fArr2 = this.f23432v;
        if (fArr2 == null) {
            A7.m.j("posArray");
            throw null;
        }
        int length3 = fArr2.length;
        for (int i18 = 0; i18 < length3; i18++) {
            AbstractC2652s abstractC2652s9 = this.f23428r;
            if (abstractC2652s9 == null) {
                A7.m.j("valueVector");
                throw null;
            }
            float[] fArr3 = this.f23432v;
            if (fArr3 == null) {
                A7.m.j("posArray");
                throw null;
            }
            abstractC2652s9.e(fArr3[i18], i18);
        }
        AbstractC2652s abstractC2652s10 = this.f23428r;
        if (abstractC2652s10 != null) {
            return abstractC2652s10;
        }
        A7.m.j("valueVector");
        throw null;
    }

    @Override // x.G0
    public final int r() {
        return this.f23425n;
    }
}
