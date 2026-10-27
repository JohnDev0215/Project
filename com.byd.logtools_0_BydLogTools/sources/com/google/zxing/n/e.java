package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: EAN13Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends p {
    static final int[] i = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] j = new int[4];

    private static void r(StringBuilder sb, int i2) throws NotFoundException {
        for (int i3 = 0; i3 < 10; i3++) {
            if (i2 == i[i3]) {
                sb.insert(0, (char) (i3 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.n.p
    protected int k(com.google.zxing.common.a aVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.j;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iL = aVar.l();
        int i2 = iArr[1];
        int i3 = 0;
        for (int i4 = 0; i4 < 6 && i2 < iL; i4++) {
            int i5 = p.i(aVar, iArr2, i2, p.f4353e);
            sb.append((char) ((i5 % 10) + 48));
            for (int i6 : iArr2) {
                i2 += i6;
            }
            if (i5 >= 10) {
                i3 |= 1 << (5 - i4);
            }
        }
        r(sb, i3);
        int i7 = p.m(aVar, i2, true, p.f4350b)[1];
        for (int i8 = 0; i8 < 6 && i7 < iL; i8++) {
            sb.append((char) (p.i(aVar, iArr2, i7, p.f4352d) + 48));
            for (int i9 : iArr2) {
                i7 += i9;
            }
        }
        return i7;
    }

    @Override // com.google.zxing.n.p
    BarcodeFormat p() {
        return BarcodeFormat.EAN_13;
    }
}
