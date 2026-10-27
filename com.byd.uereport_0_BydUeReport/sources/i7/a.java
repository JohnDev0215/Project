package i7;

import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: Base64.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f5509a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f5510b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    public static byte[] a(String str) {
        int i8;
        char cCharAt;
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        int i9 = (int) ((((long) length) * 6) / 8);
        byte[] bArr = new byte[i9];
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < length; i13++) {
            char cCharAt2 = str.charAt(i13);
            if (cCharAt2 >= 'A' && cCharAt2 <= 'Z') {
                i8 = cCharAt2 - 'A';
            } else if (cCharAt2 >= 'a' && cCharAt2 <= 'z') {
                i8 = cCharAt2 - 'G';
            } else if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                i8 = cCharAt2 + 4;
            } else if (cCharAt2 == '+' || cCharAt2 == '-') {
                i8 = 62;
            } else if (cCharAt2 == '/' || cCharAt2 == '_') {
                i8 = 63;
            } else {
                if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                    return null;
                }
            }
            i11 = (i11 << 6) | ((byte) i8);
            i10++;
            if (i10 % 4 == 0) {
                int i14 = i12 + 1;
                bArr[i12] = (byte) (i11 >> 16);
                int i15 = i14 + 1;
                bArr[i14] = (byte) (i11 >> 8);
                bArr[i15] = (byte) i11;
                i12 = i15 + 1;
            }
        }
        int i16 = i10 % 4;
        if (i16 == 1) {
            return null;
        }
        if (i16 == 2) {
            bArr[i12] = (byte) ((i11 << 12) >> 16);
            i12++;
        } else if (i16 == 3) {
            int i17 = i11 << 6;
            int i18 = i12 + 1;
            bArr[i12] = (byte) (i17 >> 16);
            i12 = i18 + 1;
            bArr[i18] = (byte) (i17 >> 8);
        }
        if (i12 == i9) {
            return bArr;
        }
        byte[] bArr2 = new byte[i12];
        System.arraycopy(bArr, 0, bArr2, 0, i12);
        return bArr2;
    }

    public static String b(byte[] bArr) {
        return c(bArr, f5509a);
    }

    public static String c(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9 += 3) {
            int i10 = i8 + 1;
            bArr3[i8] = bArr2[(bArr[i9] & 255) >> 2];
            int i11 = i10 + 1;
            int i12 = i9 + 1;
            bArr3[i10] = bArr2[((bArr[i9] & 3) << 4) | ((bArr[i12] & 255) >> 4)];
            int i13 = i11 + 1;
            int i14 = (bArr[i12] & 15) << 2;
            int i15 = i9 + 2;
            bArr3[i11] = bArr2[i14 | ((bArr[i15] & 255) >> 6)];
            i8 = i13 + 1;
            bArr3[i13] = bArr2[bArr[i15] & 63];
        }
        int length2 = bArr.length % 3;
        if (length2 == 1) {
            int i16 = i8 + 1;
            bArr3[i8] = bArr2[(bArr[length] & 255) >> 2];
            int i17 = i16 + 1;
            bArr3[i16] = bArr2[(bArr[length] & 3) << 4];
            bArr3[i17] = 61;
            bArr3[i17 + 1] = 61;
        } else if (length2 == 2) {
            int i18 = i8 + 1;
            bArr3[i8] = bArr2[(bArr[length] & 255) >> 2];
            int i19 = i18 + 1;
            int i20 = (bArr[length] & 3) << 4;
            int i21 = length + 1;
            bArr3[i18] = bArr2[((bArr[i21] & 255) >> 4) | i20];
            bArr3[i19] = bArr2[(bArr[i21] & 15) << 2];
            bArr3[i19 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e8) {
            throw new AssertionError(e8);
        }
    }

    public static String d(byte[] bArr) {
        return c(bArr, f5510b);
    }
}
