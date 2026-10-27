package com.google.zxing.datamatrix.decoder;

import com.google.zxing.datamatrix.decoder.d;

/* JADX INFO: compiled from: DataBlock.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4259a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f4260b;

    private b(int i, byte[] bArr) {
        this.f4259a = i;
        this.f4260b = bArr;
    }

    static b[] b(byte[] bArr, d dVar) {
        d.c cVarD = dVar.d();
        d.b[] bVarArrA = cVarD.a();
        int iA = 0;
        for (d.b bVar : bVarArrA) {
            iA += bVar.a();
        }
        b[] bVarArr = new b[iA];
        int i = 0;
        for (d.b bVar2 : bVarArrA) {
            int i2 = 0;
            while (i2 < bVar2.a()) {
                int iB = bVar2.b();
                bVarArr[i] = new b(iB, new byte[cVarD.b() + iB]);
                i2++;
                i++;
            }
        }
        int length = bVarArr[0].f4260b.length - cVarD.b();
        int i3 = length - 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = 0;
            while (i6 < i) {
                bVarArr[i6].f4260b[i5] = bArr[i4];
                i6++;
                i4++;
            }
        }
        boolean z = dVar.i() == 24;
        int i7 = z ? 8 : i;
        int i8 = 0;
        while (i8 < i7) {
            bVarArr[i8].f4260b[i3] = bArr[i4];
            i8++;
            i4++;
        }
        int length2 = bVarArr[0].f4260b.length;
        while (length < length2) {
            int i9 = 0;
            while (i9 < i) {
                int i10 = z ? (i9 + 8) % i : i9;
                bVarArr[i10].f4260b[(!z || i10 <= 7) ? length : length - 1] = bArr[i4];
                i9++;
                i4++;
            }
            length++;
        }
        if (i4 == bArr.length) {
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    byte[] a() {
        return this.f4260b;
    }

    int c() {
        return this.f4259a;
    }
}
