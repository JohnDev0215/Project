package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;

/* JADX INFO: compiled from: BitMatrixParser.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g f4487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f4488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4489d;

    a(com.google.zxing.common.b bVar) throws FormatException {
        int iH = bVar.h();
        if (iH < 21 || (iH & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.f4486a = bVar;
    }

    private int a(int i, int i2, int i3) {
        return this.f4489d ? this.f4486a.d(i2, i) : this.f4486a.d(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    void b() {
        int i = 0;
        while (i < this.f4486a.k()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f4486a.h(); i3++) {
                if (this.f4486a.d(i, i3) != this.f4486a.d(i3, i)) {
                    this.f4486a.c(i3, i);
                    this.f4486a.c(i, i3);
                }
            }
            i = i2;
        }
    }

    byte[] c() throws FormatException {
        e eVarD = d();
        g gVarE = e();
        DataMask dataMask = DataMask.values()[eVarD.c()];
        int iH = this.f4486a.h();
        dataMask.unmaskBitMatrix(this.f4486a, iH);
        com.google.zxing.common.b bVarA = gVarE.a();
        byte[] bArr = new byte[gVarE.h()];
        int i = iH - 1;
        boolean z = true;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i2 == 6) {
                i2--;
            }
            for (int i6 = 0; i6 < iH; i6++) {
                int i7 = z ? i - i6 : i6;
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i2 - i8;
                    if (!bVarA.d(i9, i7)) {
                        i4++;
                        i5 <<= 1;
                        if (this.f4486a.d(i9, i7)) {
                            i5 |= 1;
                        }
                        if (i4 == 8) {
                            bArr[i3] = (byte) i5;
                            i3++;
                            i4 = 0;
                            i5 = 0;
                        }
                    }
                }
            }
            z = !z;
            i2 -= 2;
        }
        if (i3 == gVarE.h()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    e d() throws FormatException {
        e eVar = this.f4488c;
        if (eVar != null) {
            return eVar;
        }
        int iA = 0;
        int iA2 = 0;
        for (int i = 0; i < 6; i++) {
            iA2 = a(i, 8, iA2);
        }
        int iA3 = a(8, 7, a(8, 8, a(7, 8, iA2)));
        for (int i2 = 5; i2 >= 0; i2--) {
            iA3 = a(8, i2, iA3);
        }
        int iH = this.f4486a.h();
        int i3 = iH - 7;
        for (int i4 = iH - 1; i4 >= i3; i4--) {
            iA = a(8, i4, iA);
        }
        for (int i5 = iH - 8; i5 < iH; i5++) {
            iA = a(i5, 8, iA);
        }
        e eVarA = e.a(iA3, iA);
        this.f4488c = eVarA;
        if (eVarA != null) {
            return eVarA;
        }
        throw FormatException.getFormatInstance();
    }

    g e() throws FormatException {
        g gVar = this.f4487b;
        if (gVar != null) {
            return gVar;
        }
        int iH = this.f4486a.h();
        int i = (iH - 17) / 4;
        if (i <= 6) {
            return g.i(i);
        }
        int i2 = iH - 11;
        int iA = 0;
        int iA2 = 0;
        for (int i3 = 5; i3 >= 0; i3--) {
            for (int i4 = iH - 9; i4 >= i2; i4--) {
                iA2 = a(i4, i3, iA2);
            }
        }
        g gVarC = g.c(iA2);
        if (gVarC != null && gVarC.e() == iH) {
            this.f4487b = gVarC;
            return gVarC;
        }
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = iH - 9; i6 >= i2; i6--) {
                iA = a(i5, i6, iA);
            }
        }
        g gVarC2 = g.c(iA);
        if (gVarC2 == null || gVarC2.e() != iH) {
            throw FormatException.getFormatInstance();
        }
        this.f4487b = gVarC2;
        return gVarC2;
    }

    void f() {
        if (this.f4488c == null) {
            return;
        }
        DataMask.values()[this.f4488c.c()].unmaskBitMatrix(this.f4486a, this.f4486a.h());
    }

    void g(boolean z) {
        this.f4487b = null;
        this.f4488c = null;
        this.f4489d = z;
    }
}
