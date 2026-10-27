package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.j;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AlignmentPatternFinder.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4515a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4517c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4518d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4519e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4520f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final float f4521g;
    private final j i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<a> f4516b = new ArrayList(5);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int[] f4522h = new int[3];

    b(com.google.zxing.common.b bVar, int i, int i2, int i3, int i4, float f2, j jVar) {
        this.f4515a = bVar;
        this.f4517c = i;
        this.f4518d = i2;
        this.f4519e = i3;
        this.f4520f = i4;
        this.f4521g = f2;
        this.i = jVar;
    }

    private static float a(int[] iArr, int i) {
        return (i - iArr[2]) - (iArr[1] / 2.0f);
    }

    private float b(int i, int i2, int i3, int i4) {
        com.google.zxing.common.b bVar = this.f4515a;
        int iH = bVar.h();
        int[] iArr = this.f4522h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i5 = i;
        while (i5 >= 0 && bVar.d(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5--;
        }
        if (i5 >= 0 && iArr[1] <= i3) {
            while (i5 >= 0 && !bVar.d(i2, i5) && iArr[0] <= i3) {
                iArr[0] = iArr[0] + 1;
                i5--;
            }
            if (iArr[0] > i3) {
                return Float.NaN;
            }
            int i6 = i + 1;
            while (i6 < iH && bVar.d(i2, i6) && iArr[1] <= i3) {
                iArr[1] = iArr[1] + 1;
                i6++;
            }
            if (i6 != iH && iArr[1] <= i3) {
                while (i6 < iH && !bVar.d(i2, i6) && iArr[2] <= i3) {
                    iArr[2] = iArr[2] + 1;
                    i6++;
                }
                if (iArr[2] <= i3 && Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 < i4 * 2 && d(iArr)) {
                    return a(iArr, i6);
                }
            }
        }
        return Float.NaN;
    }

    private boolean d(int[] iArr) {
        float f2 = this.f4521g;
        float f3 = f2 / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f2 - iArr[i]) >= f3) {
                return false;
            }
        }
        return true;
    }

    private a e(int[] iArr, int i, int i2) {
        int i3 = iArr[0] + iArr[1] + iArr[2];
        float fA = a(iArr, i2);
        float fB = b(i, (int) fA, iArr[1] * 2, i3);
        if (Float.isNaN(fB)) {
            return null;
        }
        float f2 = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (a aVar : this.f4516b) {
            if (aVar.f(f2, fB, fA)) {
                return aVar.g(fB, fA, f2);
            }
        }
        a aVar2 = new a(fA, fB, f2);
        this.f4516b.add(aVar2);
        j jVar = this.i;
        if (jVar == null) {
            return null;
        }
        jVar.a(aVar2);
        return null;
    }

    a c() throws NotFoundException {
        a aVarE;
        a aVarE2;
        int i = this.f4517c;
        int i2 = this.f4520f;
        int i3 = this.f4519e + i;
        int i4 = this.f4518d + (i2 / 2);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = ((i5 & 1) == 0 ? (i5 + 1) / 2 : -((i5 + 1) / 2)) + i4;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i7 = i;
            while (i7 < i3 && !this.f4515a.d(i7, i6)) {
                i7++;
            }
            int i8 = 0;
            while (i7 < i3) {
                if (!this.f4515a.d(i7, i6)) {
                    if (i8 == 1) {
                        i8++;
                    }
                    iArr[i8] = iArr[i8] + 1;
                } else if (i8 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i8 != 2) {
                    i8++;
                    iArr[i8] = iArr[i8] + 1;
                } else {
                    if (d(iArr) && (aVarE2 = e(iArr, i6, i7)) != null) {
                        return aVarE2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i8 = 1;
                }
                i7++;
            }
            if (d(iArr) && (aVarE = e(iArr, i6, i3)) != null) {
                return aVarE;
            }
        }
        if (this.f4516b.isEmpty()) {
            throw NotFoundException.getNotFoundInstance();
        }
        return this.f4516b.get(0);
    }
}
