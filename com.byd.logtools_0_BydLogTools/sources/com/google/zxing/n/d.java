package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: Code93Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f4327a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f4328b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f4329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final StringBuilder f4330d = new StringBuilder(20);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int[] f4331e = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        f4328b = iArr;
        f4329c = iArr[47];
    }

    private static void g(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        h(charSequence, length - 2, 20);
        h(charSequence, length - 1, 15);
    }

    private static void h(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int iIndexOf = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            iIndexOf += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i4)) * i3;
            i3++;
            if (i3 > i2) {
                i3 = 1;
            }
        }
        if (charSequence.charAt(i) != f4327a[iIndexOf % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    private static String i(CharSequence charSequence) throws FormatException {
        int i;
        char c2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt >= 'a' && cCharAt <= 'd') {
                if (i2 >= length - 1) {
                    throw FormatException.getFormatInstance();
                }
                i2++;
                char cCharAt2 = charSequence.charAt(i2);
                switch (cCharAt) {
                    case 'a':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        i = cCharAt2 - '@';
                        c2 = (char) i;
                        sb.append(c2);
                        break;
                    case 'b':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                            i = cCharAt2 - '&';
                        } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                            i = cCharAt2 - 11;
                        } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                            i = cCharAt2 + 16;
                        } else if (cCharAt2 >= 'P' && cCharAt2 <= 'S') {
                            i = cCharAt2 + '+';
                        } else {
                            if (cCharAt2 < 'T' || cCharAt2 > 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c2 = 127;
                            sb.append(c2);
                        }
                        c2 = (char) i;
                        sb.append(c2);
                        break;
                    case 'c':
                        if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                            i = cCharAt2 - ' ';
                            c2 = (char) i;
                            sb.append(c2);
                        } else {
                            if (cCharAt2 != 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c2 = ':';
                            sb.append(c2);
                        }
                        break;
                    case 'd':
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            throw FormatException.getFormatInstance();
                        }
                        i = cCharAt2 + ' ';
                        c2 = (char) i;
                        sb.append(c2);
                        break;
                    default:
                        c2 = 0;
                        sb.append(c2);
                        break;
                }
            } else {
                sb.append(cCharAt);
            }
            i2++;
        }
        return sb.toString();
    }

    private int[] j(com.google.zxing.common.a aVar) throws NotFoundException {
        int iL = aVar.l();
        int iJ = aVar.j(0);
        Arrays.fill(this.f4331e, 0);
        int[] iArr = this.f4331e;
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        int i2 = iJ;
        while (iJ < iL) {
            if (aVar.h(iJ) != z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i != length - 1) {
                    i++;
                } else {
                    if (l(iArr) == f4329c) {
                        return new int[]{i2, iJ};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i3 = i - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i3);
                    iArr[i3] = 0;
                    iArr[i] = 0;
                    i--;
                }
                iArr[i] = 1;
                z = !z;
            }
            iJ++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char k(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int[] iArr = f4328b;
            if (i2 >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (iArr[i2] == i) {
                return f4327a[i2];
            }
            i2++;
        }
    }

    private static int l(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int length = iArr.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int iRound = Math.round((iArr[i4] * 9.0f) / i);
            if (iRound <= 0 || iRound > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                for (int i5 = 0; i5 < iRound; i5++) {
                    i3 = (i3 << 1) | 1;
                }
            } else {
                i3 <<= iRound;
            }
        }
        return i3;
    }

    @Override // com.google.zxing.n.k
    public com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int iJ = aVar.j(j(aVar)[1]);
        int iL = aVar.l();
        int[] iArr = this.f4331e;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f4330d;
        sb.setLength(0);
        while (true) {
            k.e(aVar, iJ, iArr);
            int iL2 = l(iArr);
            if (iL2 < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char cK = k(iL2);
            sb.append(cK);
            int i2 = iJ;
            for (int i3 : iArr) {
                i2 += i3;
            }
            int iJ2 = aVar.j(i2);
            if (cK == '*') {
                sb.deleteCharAt(sb.length() - 1);
                int i4 = 0;
                for (int i5 : iArr) {
                    i4 += i5;
                }
                if (iJ2 == iL || !aVar.h(iJ2)) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (sb.length() < 2) {
                    throw NotFoundException.getNotFoundInstance();
                }
                g(sb);
                sb.setLength(sb.length() - 2);
                float f2 = i;
                return new com.google.zxing.h(i(sb), null, new com.google.zxing.i[]{new com.google.zxing.i((r14[1] + r14[0]) / 2.0f, f2), new com.google.zxing.i(iJ + (i4 / 2.0f), f2)}, BarcodeFormat.CODE_93);
            }
            iJ = iJ2;
        }
    }
}
