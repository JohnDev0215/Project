package c.a.a.a;

import c.a.a.g.g;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: Base64Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Charset f3015a = c.a.a.g.c.f3076b;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f3016b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public static byte[] a(CharSequence charSequence) {
        return b(c.a.a.f.b.a(charSequence, f3015a));
    }

    public static byte[] b(byte[] bArr) {
        return g.a(bArr) ? bArr : c(bArr, 0, bArr.length);
    }

    public static byte[] c(byte[] bArr, int i2, int i3) {
        if (g.a(bArr)) {
            return bArr;
        }
        c.a.a.d.i.a aVar = new c.a.a.d.i.a(i2);
        int i4 = (i2 + i3) - 1;
        int i5 = 0;
        int i6 = (i3 * 3) / 4;
        byte[] bArr2 = new byte[i6];
        while (aVar.intValue() <= i4) {
            byte bD = d(bArr, aVar, i4);
            byte bD2 = d(bArr, aVar, i4);
            byte bD3 = d(bArr, aVar, i4);
            byte bD4 = d(bArr, aVar, i4);
            if (-2 != bD2) {
                bArr2[i5] = (byte) ((bD << 2) | (bD2 >>> 4));
                i5++;
            }
            if (-2 != bD3) {
                bArr2[i5] = (byte) (((bD2 & 15) << 4) | (bD3 >>> 2));
                i5++;
            }
            if (-2 != bD4) {
                bArr2[i5] = (byte) (((bD3 & 3) << 6) | bD4);
                i5++;
            }
        }
        return i5 == i6 ? bArr2 : (byte[]) c.a.a.g.a.d(bArr2, new byte[i5], i5);
    }

    private static byte d(byte[] bArr, c.a.a.d.i.a aVar, int i2) {
        byte b2;
        while (aVar.intValue() <= i2) {
            byte b3 = bArr[aVar.intValue()];
            aVar.b();
            if (b3 > -1 && (b2 = f3016b[b3]) > -1) {
                return b2;
            }
        }
        return (byte) -2;
    }
}
