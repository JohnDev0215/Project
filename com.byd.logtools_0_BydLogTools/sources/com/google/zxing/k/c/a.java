package com.google.zxing.k.c;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.b;
import com.google.zxing.common.h;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.google.zxing.common.reedsolomon.c;
import com.google.zxing.i;

/* JADX INFO: compiled from: Detector.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f4298a = {3808, 476, 2107, 1799};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f4299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4300c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4301d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4302e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4303f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4304g;

    /* JADX INFO: renamed from: com.google.zxing.k.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Detector.java */
    static final class C0080a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f4305a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f4306b;

        C0080a(int i, int i2) {
            this.f4305a = i;
            this.f4306b = i2;
        }

        int a() {
            return this.f4305a;
        }

        int b() {
            return this.f4306b;
        }

        i c() {
            return new i(a(), b());
        }

        public String toString() {
            return "<" + this.f4305a + ' ' + this.f4306b + '>';
        }
    }

    public a(b bVar) {
        this.f4299b = bVar;
    }

    private static float b(i iVar, i iVar2) {
        return com.google.zxing.common.l.a.a(iVar.c(), iVar.d(), iVar2.c(), iVar2.d());
    }

    private static float c(C0080a c0080a, C0080a c0080a2) {
        return com.google.zxing.common.l.a.b(c0080a.a(), c0080a.b(), c0080a2.a(), c0080a2.b());
    }

    private static i[] d(i[] iVarArr, int i, int i2) {
        float f2 = i2 / (i * 2.0f);
        float fC = iVarArr[0].c() - iVarArr[2].c();
        float fD = iVarArr[0].d() - iVarArr[2].d();
        float fC2 = (iVarArr[0].c() + iVarArr[2].c()) / 2.0f;
        float fD2 = (iVarArr[0].d() + iVarArr[2].d()) / 2.0f;
        float f3 = fC * f2;
        float f4 = fD * f2;
        i iVar = new i(fC2 + f3, fD2 + f4);
        i iVar2 = new i(fC2 - f3, fD2 - f4);
        float fC3 = iVarArr[1].c() - iVarArr[3].c();
        float fD3 = iVarArr[1].d() - iVarArr[3].d();
        float fC4 = (iVarArr[1].c() + iVarArr[3].c()) / 2.0f;
        float fD4 = (iVarArr[1].d() + iVarArr[3].d()) / 2.0f;
        float f5 = fC3 * f2;
        float f6 = f2 * fD3;
        return new i[]{iVar, new i(fC4 + f5, fD4 + f6), iVar2, new i(fC4 - f5, fD4 - f6)};
    }

    private void e(i[] iVarArr) throws NotFoundException {
        long j;
        long j2;
        if (!o(iVarArr[0]) || !o(iVarArr[1]) || !o(iVarArr[2]) || !o(iVarArr[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = this.f4303f * 2;
        int[] iArr = {r(iVarArr[0], iVarArr[1], i), r(iVarArr[1], iVarArr[2], i), r(iVarArr[2], iVarArr[3], i), r(iVarArr[3], iVarArr[0], i)};
        this.f4304g = m(iArr, i);
        long j3 = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = iArr[(this.f4304g + i2) % 4];
            if (this.f4300c) {
                j = j3 << 7;
                j2 = (i3 >> 1) & 127;
            } else {
                j = j3 << 10;
                j2 = ((i3 >> 2) & 992) + ((i3 >> 1) & 31);
            }
            j3 = j + j2;
        }
        int iH = h(j3, this.f4300c);
        if (this.f4300c) {
            this.f4301d = (iH >> 6) + 1;
            this.f4302e = (iH & 63) + 1;
        } else {
            this.f4301d = (iH >> 11) + 1;
            this.f4302e = (iH & 2047) + 1;
        }
    }

    private i[] f(C0080a c0080a) throws NotFoundException {
        this.f4303f = 1;
        C0080a c0080a2 = c0080a;
        C0080a c0080a3 = c0080a2;
        C0080a c0080a4 = c0080a3;
        boolean z = true;
        while (this.f4303f < 9) {
            C0080a c0080aJ = j(c0080a, z, 1, -1);
            C0080a c0080aJ2 = j(c0080a2, z, 1, 1);
            C0080a c0080aJ3 = j(c0080a3, z, -1, 1);
            C0080a c0080aJ4 = j(c0080a4, z, -1, -1);
            if (this.f4303f > 2) {
                double dC = (c(c0080aJ4, c0080aJ) * this.f4303f) / (c(c0080a4, c0080a) * (this.f4303f + 2));
                if (dC < 0.75d || dC > 1.25d || !p(c0080aJ, c0080aJ2, c0080aJ3, c0080aJ4)) {
                    break;
                }
            }
            z = !z;
            this.f4303f++;
            c0080a4 = c0080aJ4;
            c0080a = c0080aJ;
            c0080a2 = c0080aJ2;
            c0080a3 = c0080aJ3;
        }
        int i = this.f4303f;
        if (i != 5 && i != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.f4300c = i == 5;
        i[] iVarArr = {new i(c0080a.a() + 0.5f, c0080a.b() - 0.5f), new i(c0080a2.a() + 0.5f, c0080a2.b() + 0.5f), new i(c0080a3.a() - 0.5f, c0080a3.b() + 0.5f), new i(c0080a4.a() - 0.5f, c0080a4.b() - 0.5f)};
        int i2 = this.f4303f;
        return d(iVarArr, (i2 * 2) - 3, i2 * 2);
    }

    private int g(C0080a c0080a, C0080a c0080a2) {
        float fC = c(c0080a, c0080a2);
        float fA = (c0080a2.a() - c0080a.a()) / fC;
        float fB = (c0080a2.b() - c0080a.b()) / fC;
        float fA2 = c0080a.a();
        float fB2 = c0080a.b();
        boolean zD = this.f4299b.d(c0080a.a(), c0080a.b());
        int iCeil = (int) Math.ceil(fC);
        int i = 0;
        for (int i2 = 0; i2 < iCeil; i2++) {
            fA2 += fA;
            fB2 += fB;
            if (this.f4299b.d(com.google.zxing.common.l.a.c(fA2), com.google.zxing.common.l.a.c(fB2)) != zD) {
                i++;
            }
        }
        float f2 = i / fC;
        if (f2 <= 0.1f || f2 >= 0.9f) {
            return (f2 <= 0.1f) == zD ? 1 : -1;
        }
        return 0;
    }

    private static int h(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new c(com.google.zxing.common.reedsolomon.a.f4237d).a(iArr, i3);
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                i5 = (i5 << 4) + iArr[i6];
            }
            return i5;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private int i() {
        if (this.f4300c) {
            return (this.f4301d * 4) + 11;
        }
        int i = this.f4301d;
        return i <= 4 ? (i * 4) + 15 : (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }

    private C0080a j(C0080a c0080a, boolean z, int i, int i2) {
        int iA = c0080a.a() + i;
        int iB = c0080a.b();
        while (true) {
            iB += i2;
            if (!n(iA, iB) || this.f4299b.d(iA, iB) != z) {
                break;
            }
            iA += i;
        }
        int i3 = iA - i;
        int i4 = iB - i2;
        while (n(i3, i4) && this.f4299b.d(i3, i4) == z) {
            i3 += i;
        }
        int i5 = i3 - i;
        while (n(i5, i4) && this.f4299b.d(i5, i4) == z) {
            i4 += i2;
        }
        return new C0080a(i5, i4 - i2);
    }

    private C0080a k() {
        i iVarC;
        i iVar;
        i iVar2;
        i iVar3;
        i iVarC2;
        i iVarC3;
        i iVarC4;
        i iVarC5;
        try {
            i[] iVarArrC = new com.google.zxing.common.l.b(this.f4299b).c();
            iVar2 = iVarArrC[0];
            iVar3 = iVarArrC[1];
            iVar = iVarArrC[2];
            iVarC = iVarArrC[3];
        } catch (NotFoundException unused) {
            int iK = this.f4299b.k() / 2;
            int iH = this.f4299b.h() / 2;
            int i = iK + 7;
            int i2 = iH - 7;
            i iVarC6 = j(new C0080a(i, i2), false, 1, -1).c();
            int i3 = iH + 7;
            i iVarC7 = j(new C0080a(i, i3), false, 1, 1).c();
            int i4 = iK - 7;
            i iVarC8 = j(new C0080a(i4, i3), false, -1, 1).c();
            iVarC = j(new C0080a(i4, i2), false, -1, -1).c();
            iVar = iVarC8;
            iVar2 = iVarC6;
            iVar3 = iVarC7;
        }
        int iC = com.google.zxing.common.l.a.c((((iVar2.c() + iVarC.c()) + iVar3.c()) + iVar.c()) / 4.0f);
        int iC2 = com.google.zxing.common.l.a.c((((iVar2.d() + iVarC.d()) + iVar3.d()) + iVar.d()) / 4.0f);
        try {
            i[] iVarArrC2 = new com.google.zxing.common.l.b(this.f4299b, 15, iC, iC2).c();
            iVarC2 = iVarArrC2[0];
            iVarC3 = iVarArrC2[1];
            iVarC4 = iVarArrC2[2];
            iVarC5 = iVarArrC2[3];
        } catch (NotFoundException unused2) {
            int i5 = iC + 7;
            int i6 = iC2 - 7;
            iVarC2 = j(new C0080a(i5, i6), false, 1, -1).c();
            int i7 = iC2 + 7;
            iVarC3 = j(new C0080a(i5, i7), false, 1, 1).c();
            int i8 = iC - 7;
            iVarC4 = j(new C0080a(i8, i7), false, -1, 1).c();
            iVarC5 = j(new C0080a(i8, i6), false, -1, -1).c();
        }
        return new C0080a(com.google.zxing.common.l.a.c((((iVarC2.c() + iVarC5.c()) + iVarC3.c()) + iVarC4.c()) / 4.0f), com.google.zxing.common.l.a.c((((iVarC2.d() + iVarC5.d()) + iVarC3.d()) + iVarC4.d()) / 4.0f));
    }

    private i[] l(i[] iVarArr) {
        return d(iVarArr, this.f4303f * 2, i());
    }

    private static int m(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + ((i3 >> (i - 2)) << 1) + (i3 & 1);
        }
        int i4 = ((i2 & 1) << 11) + (i2 >> 1);
        for (int i5 = 0; i5 < 4; i5++) {
            if (Integer.bitCount(f4298a[i5] ^ i4) <= 2) {
                return i5;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private boolean n(int i, int i2) {
        return i >= 0 && i < this.f4299b.k() && i2 > 0 && i2 < this.f4299b.h();
    }

    private boolean o(i iVar) {
        return n(com.google.zxing.common.l.a.c(iVar.c()), com.google.zxing.common.l.a.c(iVar.d()));
    }

    private boolean p(C0080a c0080a, C0080a c0080a2, C0080a c0080a3, C0080a c0080a4) {
        C0080a c0080a5 = new C0080a(c0080a.a() - 3, c0080a.b() + 3);
        C0080a c0080a6 = new C0080a(c0080a2.a() - 3, c0080a2.b() - 3);
        C0080a c0080a7 = new C0080a(c0080a3.a() + 3, c0080a3.b() - 3);
        C0080a c0080a8 = new C0080a(c0080a4.a() + 3, c0080a4.b() + 3);
        int iG = g(c0080a8, c0080a5);
        return iG != 0 && g(c0080a5, c0080a6) == iG && g(c0080a6, c0080a7) == iG && g(c0080a7, c0080a8) == iG;
    }

    private b q(b bVar, i iVar, i iVar2, i iVar3, i iVar4) throws NotFoundException {
        h hVarB = h.b();
        int i = i();
        float f2 = i / 2.0f;
        int i2 = this.f4303f;
        float f3 = f2 - i2;
        float f4 = f2 + i2;
        return hVarB.c(bVar, i, i, f3, f3, f4, f3, f4, f4, f3, f4, iVar.c(), iVar.d(), iVar2.c(), iVar2.d(), iVar3.c(), iVar3.d(), iVar4.c(), iVar4.d());
    }

    private int r(i iVar, i iVar2, int i) {
        float fB = b(iVar, iVar2);
        float f2 = fB / i;
        float fC = iVar.c();
        float fD = iVar.d();
        float fC2 = ((iVar2.c() - iVar.c()) * f2) / fB;
        float fD2 = (f2 * (iVar2.d() - iVar.d())) / fB;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f3 = i3;
            if (this.f4299b.d(com.google.zxing.common.l.a.c((f3 * fC2) + fC), com.google.zxing.common.l.a.c((f3 * fD2) + fD))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    public com.google.zxing.k.a a(boolean z) throws NotFoundException {
        i[] iVarArrF = f(k());
        if (z) {
            i iVar = iVarArrF[0];
            iVarArrF[0] = iVarArrF[2];
            iVarArrF[2] = iVar;
        }
        e(iVarArrF);
        b bVar = this.f4299b;
        int i = this.f4304g;
        return new com.google.zxing.k.a(q(bVar, iVarArrF[i % 4], iVarArrF[(i + 1) % 4], iVarArrF[(i + 2) % 4], iVarArrF[(i + 3) % 4]), l(iVarArrF), this.f4300c, this.f4302e, this.f4301d);
    }
}
