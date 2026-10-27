package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: EAN8Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends p {
    private final int[] i = new int[4];

    @Override // com.google.zxing.n.p
    protected int k(com.google.zxing.common.a aVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.i;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iL = aVar.l();
        int i = iArr[1];
        for (int i2 = 0; i2 < 4 && i < iL; i2++) {
            sb.append((char) (p.i(aVar, iArr2, i, p.f4352d) + 48));
            for (int i3 : iArr2) {
                i += i3;
            }
        }
        int i4 = p.m(aVar, i, true, p.f4350b)[1];
        for (int i5 = 0; i5 < 4 && i4 < iL; i5++) {
            sb.append((char) (p.i(aVar, iArr2, i4, p.f4352d) + 48));
            for (int i6 : iArr2) {
                i4 += i6;
            }
        }
        return i4;
    }

    @Override // com.google.zxing.n.p
    BarcodeFormat p() {
        return BarcodeFormat.EAN_8;
    }
}
