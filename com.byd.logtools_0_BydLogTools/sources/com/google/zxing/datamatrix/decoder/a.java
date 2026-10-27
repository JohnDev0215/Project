package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;

/* JADX INFO: compiled from: BitMatrixParser.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.zxing.common.b f4257b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f4258c;

    a(com.google.zxing.common.b bVar) throws FormatException {
        int iH = bVar.h();
        if (iH < 8 || iH > 144 || (iH & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.f4258c = j(bVar);
        com.google.zxing.common.b bVarA = a(bVar);
        this.f4256a = bVarA;
        this.f4257b = new com.google.zxing.common.b(bVarA.k(), bVarA.h());
    }

    private com.google.zxing.common.b a(com.google.zxing.common.b bVar) {
        int iF = this.f4258c.f();
        int iE = this.f4258c.e();
        if (bVar.h() != iF) {
            throw new IllegalArgumentException("Dimension of bitMatrix must match the version size");
        }
        int iC = this.f4258c.c();
        int iB = this.f4258c.b();
        int i = iF / iC;
        int i2 = iE / iB;
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(i2 * iB, i * iC);
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * iC;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = i5 * iB;
                for (int i7 = 0; i7 < iC; i7++) {
                    int i8 = ((iC + 2) * i3) + 1 + i7;
                    int i9 = i4 + i7;
                    for (int i10 = 0; i10 < iB; i10++) {
                        if (bVar.d(((iB + 2) * i5) + 1 + i10, i8)) {
                            bVar2.m(i6 + i10, i9);
                        }
                    }
                }
            }
        }
        return bVar2;
    }

    private int d(int i, int i2) {
        int i3 = i - 1;
        int i4 = (h(i3, 0, i, i2) ? 1 : 0) << 1;
        if (h(i3, 1, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (h(i3, 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (h(0, i2 - 2, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        int i8 = i2 - 1;
        if (h(0, i8, i, i2)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        if (h(1, i8, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        if (h(2, i8, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        return h(3, i8, i, i2) ? i11 | 1 : i11;
    }

    private int e(int i, int i2) {
        int i3 = (h(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (h(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (h(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (h(0, i2 - 4, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (h(0, i2 - 3, i, i2)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (h(0, i2 - 2, i, i2)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i2 - 1;
        if (h(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        return h(1, i9, i, i2) ? i10 | 1 : i10;
    }

    private int f(int i, int i2) {
        int i3 = i - 1;
        int i4 = (h(i3, 0, i, i2) ? 1 : 0) << 1;
        int i5 = i2 - 1;
        if (h(i3, i5, i, i2)) {
            i4 |= 1;
        }
        int i6 = i4 << 1;
        int i7 = i2 - 3;
        if (h(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        int i9 = i2 - 2;
        if (h(0, i9, i, i2)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (h(0, i5, i, i2)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (h(1, i7, i, i2)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (h(1, i9, i, i2)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        return h(1, i5, i, i2) ? i13 | 1 : i13;
    }

    private int g(int i, int i2) {
        int i3 = (h(i + (-3), 0, i, i2) ? 1 : 0) << 1;
        if (h(i - 2, 0, i, i2)) {
            i3 |= 1;
        }
        int i4 = i3 << 1;
        if (h(i - 1, 0, i, i2)) {
            i4 |= 1;
        }
        int i5 = i4 << 1;
        if (h(0, i2 - 2, i, i2)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        int i7 = i2 - 1;
        if (h(0, i7, i, i2)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        if (h(1, i7, i, i2)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        if (h(2, i7, i, i2)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        return h(3, i7, i, i2) ? i10 | 1 : i10;
    }

    private boolean h(int i, int i2, int i3, int i4) {
        if (i < 0) {
            i += i3;
            i2 += 4 - ((i3 + 4) & 7);
        }
        if (i2 < 0) {
            i2 += i4;
            i += 4 - ((i4 + 4) & 7);
        }
        this.f4257b.m(i2, i);
        return this.f4256a.d(i2, i);
    }

    private int i(int i, int i2, int i3, int i4) {
        int i5 = i - 2;
        int i6 = i2 - 2;
        int i7 = (h(i5, i6, i3, i4) ? 1 : 0) << 1;
        int i8 = i2 - 1;
        if (h(i5, i8, i3, i4)) {
            i7 |= 1;
        }
        int i9 = i7 << 1;
        int i10 = i - 1;
        if (h(i10, i6, i3, i4)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (h(i10, i8, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (h(i10, i2, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (h(i, i6, i3, i4)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (h(i, i8, i3, i4)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        return h(i, i2, i3, i4) ? i15 | 1 : i15;
    }

    private static d j(com.google.zxing.common.b bVar) throws FormatException {
        return d.h(bVar.h(), bVar.k());
    }

    d b() {
        return this.f4258c;
    }

    byte[] c() throws FormatException {
        byte[] bArr = new byte[this.f4258c.g()];
        int iH = this.f4256a.h();
        int iK = this.f4256a.k();
        int i = 0;
        int i2 = 4;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            if (i2 == iH && i == 0 && !z) {
                bArr[i3] = (byte) d(iH, iK);
                i2 -= 2;
                i += 2;
                i3++;
                z = true;
            } else {
                int i4 = iH - 2;
                if (i2 == i4 && i == 0 && (iK & 3) != 0 && !z2) {
                    bArr[i3] = (byte) e(iH, iK);
                    i2 -= 2;
                    i += 2;
                    i3++;
                    z2 = true;
                } else if (i2 == iH + 4 && i == 2 && (iK & 7) == 0 && !z3) {
                    bArr[i3] = (byte) f(iH, iK);
                    i2 -= 2;
                    i += 2;
                    i3++;
                    z3 = true;
                } else if (i2 == i4 && i == 0 && (iK & 7) == 4 && !z4) {
                    bArr[i3] = (byte) g(iH, iK);
                    i2 -= 2;
                    i += 2;
                    i3++;
                    z4 = true;
                } else {
                    do {
                        if (i2 < iH && i >= 0 && !this.f4257b.d(i, i2)) {
                            bArr[i3] = (byte) i(i2, i, iH, iK);
                            i3++;
                        }
                        i2 -= 2;
                        i += 2;
                        if (i2 < 0) {
                            break;
                        }
                    } while (i < iK);
                    int i5 = i2 + 1;
                    int i6 = i + 3;
                    do {
                        if (i5 >= 0 && i6 < iK && !this.f4257b.d(i6, i5)) {
                            bArr[i3] = (byte) i(i5, i6, iH, iK);
                            i3++;
                        }
                        i5 += 2;
                        i6 -= 2;
                        if (i5 >= iH) {
                            break;
                        }
                    } while (i6 >= 0);
                    i2 = i5 + 3;
                    i = i6 + 1;
                }
            }
            if (i2 >= iH && i >= iK) {
                break;
            }
        }
        if (i3 == this.f4258c.g()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }
}
