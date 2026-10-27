package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.ResultMetadataType;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: UPCEANReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final int[] f4349a = {1, 1, 1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f4350b = {1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f4351c = {1, 1, 1, 1, 1, 1};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int[][] f4352d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final int[][] f4353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final StringBuilder f4354f = new StringBuilder(20);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final o f4355g = new o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final g f4356h = new g();

    static {
        int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        f4352d = iArr;
        int[][] iArr2 = new int[20][];
        f4353e = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr3 = f4352d[i - 10];
            int[] iArr4 = new int[iArr3.length];
            for (int i2 = 0; i2 < iArr3.length; i2++) {
                iArr4[i2] = iArr3[(iArr3.length - i2) - 1];
            }
            f4353e[i] = iArr4;
        }
    }

    protected p() {
    }

    static boolean h(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = length - 1;
        return q(charSequence.subSequence(0, i)) == Character.digit(charSequence.charAt(i), 10);
    }

    static int i(com.google.zxing.common.a aVar, int[] iArr, int i, int[][] iArr2) throws NotFoundException {
        k.e(aVar, i, iArr);
        int length = iArr2.length;
        float f2 = 0.48f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float fD = k.d(iArr, iArr2[i3], 0.7f);
            if (fD < f2) {
                i2 = i3;
                f2 = fD;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int[] m(com.google.zxing.common.a aVar, int i, boolean z, int[] iArr) throws NotFoundException {
        return n(aVar, i, z, iArr, new int[iArr.length]);
    }

    private static int[] n(com.google.zxing.common.a aVar, int i, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int iL = aVar.l();
        int iK = z ? aVar.k(i) : aVar.j(i);
        int length = iArr.length;
        boolean z2 = z;
        int i2 = 0;
        int i3 = iK;
        while (iK < iL) {
            if (aVar.h(iK) != z2) {
                iArr2[i2] = iArr2[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else {
                    if (k.d(iArr2, iArr, 0.7f) < 0.48f) {
                        return new int[]{i3, iK};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i4 = i2 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i4);
                    iArr2[i4] = 0;
                    iArr2[i2] = 0;
                    i2--;
                }
                iArr2[i2] = 1;
                z2 = !z2;
            }
            iK++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int[] o(com.google.zxing.common.a aVar) throws NotFoundException {
        int[] iArr = new int[f4349a.length];
        int[] iArrN = null;
        boolean zN = false;
        int i = 0;
        while (!zN) {
            int[] iArr2 = f4349a;
            Arrays.fill(iArr, 0, iArr2.length, 0);
            iArrN = n(aVar, i, false, iArr2, iArr);
            int i2 = iArrN[0];
            int i3 = iArrN[1];
            int i4 = i2 - (i3 - i2);
            if (i4 >= 0) {
                zN = aVar.n(i4, i2, false);
            }
            i = i3;
        }
        return iArrN;
    }

    static int q(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            int iCharAt = charSequence.charAt(i2) - '0';
            if (iCharAt < 0 || iCharAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i += iCharAt;
        }
        int i3 = i * 3;
        for (int i4 = length - 2; i4 >= 0; i4 -= 2) {
            int iCharAt2 = charSequence.charAt(i4) - '0';
            if (iCharAt2 < 0 || iCharAt2 > 9) {
                throw FormatException.getFormatInstance();
            }
            i3 += iCharAt2;
        }
        return (1000 - i3) % 10;
    }

    @Override // com.google.zxing.n.k
    public com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return l(i, aVar, o(aVar), map);
    }

    boolean g(String str) throws FormatException {
        return h(str);
    }

    int[] j(com.google.zxing.common.a aVar, int i) throws NotFoundException {
        return m(aVar, i, false, f4349a);
    }

    protected abstract int k(com.google.zxing.common.a aVar, int[] iArr, StringBuilder sb) throws NotFoundException;

    public com.google.zxing.h l(int i, com.google.zxing.common.a aVar, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int length;
        String strC;
        com.google.zxing.j jVar = map == null ? null : (com.google.zxing.j) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        boolean z = true;
        if (jVar != null) {
            jVar.a(new com.google.zxing.i((iArr[0] + iArr[1]) / 2.0f, i));
        }
        StringBuilder sb = this.f4354f;
        sb.setLength(0);
        int iK = k(aVar, iArr, sb);
        if (jVar != null) {
            jVar.a(new com.google.zxing.i(iK, i));
        }
        int[] iArrJ = j(aVar, iK);
        if (jVar != null) {
            jVar.a(new com.google.zxing.i((iArrJ[0] + iArrJ[1]) / 2.0f, i));
        }
        int i2 = iArrJ[1];
        int i3 = (i2 - iArrJ[0]) + i2;
        if (i3 >= aVar.l() || !aVar.n(i2, i3, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String string = sb.toString();
        if (string.length() < 8) {
            throw FormatException.getFormatInstance();
        }
        if (!g(string)) {
            throw ChecksumException.getChecksumInstance();
        }
        BarcodeFormat barcodeFormatP = p();
        float f2 = i;
        com.google.zxing.h hVar = new com.google.zxing.h(string, null, new com.google.zxing.i[]{new com.google.zxing.i((iArr[1] + iArr[0]) / 2.0f, f2), new com.google.zxing.i((iArrJ[1] + iArrJ[0]) / 2.0f, f2)}, barcodeFormatP);
        try {
            com.google.zxing.h hVarA = this.f4355g.a(i, aVar, iArrJ[1]);
            hVar.h(ResultMetadataType.UPC_EAN_EXTENSION, hVarA.f());
            hVar.g(hVarA.d());
            hVar.a(hVarA.e());
            length = hVarA.f().length();
        } catch (ReaderException unused) {
            length = 0;
        }
        int[] iArr2 = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS) : null;
        if (iArr2 != null) {
            int length2 = iArr2.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length2) {
                    z = false;
                    break;
                }
                if (length == iArr2[i4]) {
                    break;
                }
                i4++;
            }
            if (!z) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if ((barcodeFormatP == BarcodeFormat.EAN_13 || barcodeFormatP == BarcodeFormat.UPC_A) && (strC = this.f4356h.c(string)) != null) {
            hVar.h(ResultMetadataType.POSSIBLE_COUNTRY, strC);
        }
        return hVar;
    }

    abstract BarcodeFormat p();
}
