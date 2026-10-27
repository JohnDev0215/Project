package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import java.util.Map;

/* JADX INFO: compiled from: ITFReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f4334a = {6, 8, 10, 12, 14};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f4335b = {1, 1, 1, 1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[][] f4336c = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[][] f4337d = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4338e = -1;

    private static int g(int[] iArr) throws NotFoundException {
        int length = f4337d.length;
        float f2 = 0.38f;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            float fD = k.d(iArr, f4337d[i2], 0.5f);
            if (fD < f2) {
                i = i2;
                f2 = fD;
            } else if (fD == f2) {
                i = -1;
            }
        }
        if (i >= 0) {
            return i % 10;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] h(com.google.zxing.common.a aVar) throws NotFoundException {
        int[] iArrK;
        aVar.p();
        try {
            int iL = l(aVar);
            try {
                iArrK = k(aVar, iL, f4336c[0]);
            } catch (NotFoundException unused) {
                iArrK = k(aVar, iL, f4336c[1]);
            }
            m(aVar, iArrK[0]);
            int i = iArrK[0];
            iArrK[0] = aVar.l() - iArrK[1];
            iArrK[1] = aVar.l() - i;
            return iArrK;
        } finally {
            aVar.p();
        }
    }

    private static void i(com.google.zxing.common.a aVar, int i, int i2, StringBuilder sb) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            k.e(aVar, i, iArr);
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = i3 * 2;
                iArr2[i3] = iArr[i4];
                iArr3[i3] = iArr[i4 + 1];
            }
            sb.append((char) (g(iArr2) + 48));
            sb.append((char) (g(iArr3) + 48));
            for (int i5 = 0; i5 < 10; i5++) {
                i += iArr[i5];
            }
        }
    }

    private int[] j(com.google.zxing.common.a aVar) throws NotFoundException {
        int[] iArrK = k(aVar, l(aVar), f4335b);
        this.f4338e = (iArrK[1] - iArrK[0]) / 4;
        m(aVar, iArrK[0]);
        return iArrK;
    }

    private static int[] k(com.google.zxing.common.a aVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int iL = aVar.l();
        int i2 = i;
        boolean z = false;
        int i3 = 0;
        while (i < iL) {
            if (aVar.h(i) != z) {
                iArr2[i3] = iArr2[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else {
                    if (k.d(iArr2, iArr, 0.5f) < 0.38f) {
                        return new int[]{i2, i};
                    }
                    i2 += iArr2[0] + iArr2[1];
                    int i4 = i3 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i4);
                    iArr2[i4] = 0;
                    iArr2[i3] = 0;
                    i3--;
                }
                iArr2[i3] = 1;
                z = !z;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int l(com.google.zxing.common.a aVar) throws NotFoundException {
        int iL = aVar.l();
        int iJ = aVar.j(0);
        if (iJ != iL) {
            return iJ;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void m(com.google.zxing.common.a aVar, int i) throws NotFoundException {
        int i2 = this.f4338e * 10;
        if (i2 >= i) {
            i2 = i;
        }
        for (int i3 = i - 1; i2 > 0 && i3 >= 0 && !aVar.h(i3); i3--) {
            i2--;
        }
        if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    @Override // com.google.zxing.n.k
    public com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        boolean z;
        int[] iArrJ = j(aVar);
        int[] iArrH = h(aVar);
        StringBuilder sb = new StringBuilder(20);
        i(aVar, iArrJ[1], iArrH[0], sb);
        String string = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = f4334a;
        }
        int length = string.length();
        int length2 = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length2) {
                z = false;
                break;
            }
            int i4 = iArr[i2];
            if (length == i4) {
                z = true;
                break;
            }
            if (i4 > i3) {
                i3 = i4;
            }
            i2++;
        }
        if (!z && length > i3) {
            z = true;
        }
        if (!z) {
            throw FormatException.getFormatInstance();
        }
        float f2 = i;
        return new com.google.zxing.h(string, null, new com.google.zxing.i[]{new com.google.zxing.i(iArrJ[1], f2), new com.google.zxing.i(iArrH[0], f2)}, BarcodeFormat.ITF);
    }
}
