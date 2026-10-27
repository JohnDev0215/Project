package c.a.a.a;

import c.a.a.g.k;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: Base64Encoder.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Charset f3017a = c.a.a.g.c.f3076b;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f3018b = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final byte[] f3019c = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    public static String a(byte[] bArr) {
        return k.t(b(bArr, false), f3017a);
    }

    public static byte[] b(byte[] bArr, boolean z) {
        return c(bArr, z, false);
    }

    public static byte[] c(byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        if (length == 0) {
            return new byte[0];
        }
        int i2 = (length / 3) * 3;
        int i3 = length - 1;
        int i4 = ((i3 / 3) + 1) << 2;
        int i5 = i4 + (z ? ((i4 - 1) / 76) << 1 : 0);
        byte[] bArr2 = new byte[i5];
        byte[] bArr3 = z2 ? f3019c : f3018b;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < i2) {
            int i9 = i6 + 1;
            int i10 = i9 + 1;
            int i11 = ((bArr[i6] & 255) << 16) | ((bArr[i9] & 255) << 8);
            int i12 = i10 + 1;
            int i13 = i11 | (bArr[i10] & 255);
            int i14 = i7 + 1;
            bArr2[i7] = bArr3[(i13 >>> 18) & 63];
            int i15 = i14 + 1;
            bArr2[i14] = bArr3[(i13 >>> 12) & 63];
            int i16 = i15 + 1;
            bArr2[i15] = bArr3[(i13 >>> 6) & 63];
            i7 = i16 + 1;
            bArr2[i16] = bArr3[i13 & 63];
            if (z && (i8 = i8 + 1) == 19 && i7 < i5 - 2) {
                int i17 = i7 + 1;
                bArr2[i7] = 13;
                i7 = i17 + 1;
                bArr2[i17] = 10;
                i8 = 0;
            }
            i6 = i12;
        }
        int i18 = length - i2;
        if (i18 > 0) {
            int i19 = (i18 == 2 ? (bArr[i3] & 255) << 2 : 0) | ((bArr[i2] & 255) << 10);
            bArr2[i5 - 4] = bArr3[i19 >> 12];
            bArr2[i5 - 3] = bArr3[(i19 >>> 6) & 63];
            if (z2) {
                int i20 = i5 - 2;
                if (2 == i18) {
                    bArr2[i20] = bArr3[i19 & 63];
                    i20++;
                }
                byte[] bArr4 = new byte[i20];
                System.arraycopy(bArr2, 0, bArr4, 0, i20);
                return bArr4;
            }
            bArr2[i5 - 2] = i18 == 2 ? bArr3[i19 & 63] : (byte) 61;
            bArr2[i5 - 1] = 61;
        }
        return bArr2;
    }
}
