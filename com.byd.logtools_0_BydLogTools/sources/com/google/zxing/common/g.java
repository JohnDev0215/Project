package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: GlobalHistogramBinarizer.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends com.google.zxing.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f4212b = new byte[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f4213c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int[] f4214d;

    public g(com.google.zxing.d dVar) {
        super(dVar);
        this.f4213c = f4212b;
        this.f4214d = new int[32];
    }

    private static int g(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i6) {
                i5 = i7;
                i6 = i9;
            }
        }
        if (i3 <= i5) {
            int i10 = i3;
            i3 = i5;
            i5 = i10;
        }
        if (i3 - i5 <= length / 16) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i11 = i3 - 1;
        int i12 = -1;
        int i13 = i11;
        while (i11 > i5) {
            int i14 = i11 - i5;
            int i15 = i14 * i14 * (i3 - i11) * (i2 - iArr[i11]);
            if (i15 > i12) {
                i13 = i11;
                i12 = i15;
            }
            i11--;
        }
        return i13 << 3;
    }

    private void h(int i) {
        if (this.f4213c.length < i) {
            this.f4213c = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.f4214d[i2] = 0;
        }
    }

    @Override // com.google.zxing.a
    public com.google.zxing.a a(com.google.zxing.d dVar) {
        return new g(dVar);
    }

    @Override // com.google.zxing.a
    public b b() throws NotFoundException {
        com.google.zxing.d dVarE = e();
        int iD = dVarE.d();
        int iA = dVarE.a();
        b bVar = new b(iD, iA);
        h(iD);
        int[] iArr = this.f4214d;
        for (int i = 1; i < 5; i++) {
            byte[] bArrC = dVarE.c((iA * i) / 5, this.f4213c);
            int i2 = (iD << 2) / 5;
            for (int i3 = iD / 5; i3 < i2; i3++) {
                int i4 = (bArrC[i3] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int iG = g(iArr);
        byte[] bArrB = dVarE.b();
        for (int i5 = 0; i5 < iA; i5++) {
            int i6 = i5 * iD;
            for (int i7 = 0; i7 < iD; i7++) {
                if ((bArrB[i6 + i7] & 255) < iG) {
                    bVar.m(i7, i5);
                }
            }
        }
        return bVar;
    }

    @Override // com.google.zxing.a
    public a c(int i, a aVar) throws NotFoundException {
        com.google.zxing.d dVarE = e();
        int iD = dVarE.d();
        if (aVar == null || aVar.l() < iD) {
            aVar = new a(iD);
        } else {
            aVar.d();
        }
        h(iD);
        byte[] bArrC = dVarE.c(i, this.f4213c);
        int[] iArr = this.f4214d;
        for (int i2 = 0; i2 < iD; i2++) {
            int i3 = (bArrC[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        int iG = g(iArr);
        if (iD < 3) {
            for (int i4 = 0; i4 < iD; i4++) {
                if ((bArrC[i4] & 255) < iG) {
                    aVar.q(i4);
                }
            }
        } else {
            int i5 = 1;
            int i6 = bArrC[0] & 255;
            int i7 = bArrC[1] & 255;
            while (i5 < iD - 1) {
                int i8 = i5 + 1;
                int i9 = bArrC[i8] & 255;
                if ((((i7 << 2) - i6) - i9) / 2 < iG) {
                    aVar.q(i5);
                }
                i6 = i7;
                i5 = i8;
                i7 = i9;
            }
        }
        return aVar;
    }
}
