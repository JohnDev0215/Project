package c.a.a.a;

import cn.hutool.core.lang.mutable.MutableInt;
import cn.hutool.core.util.g;
import java.nio.charset.Charset;
import okio.Utf8;

/* JADX INFO: compiled from: Base64Decoder.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Charset f2872a = cn.hutool.core.util.c.f2949b;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f2873b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, Utf8.REPLACEMENT_BYTE, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, Utf8.REPLACEMENT_BYTE, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public static byte[] a(CharSequence charSequence) {
        return b(c.a.a.d.b.b(charSequence, f2872a));
    }

    public static byte[] b(byte[] bArr) {
        return g.a(bArr) ? bArr : c(bArr, 0, bArr.length);
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        if (g.a(bArr)) {
            return bArr;
        }
        MutableInt mutableInt = new MutableInt(i);
        int i3 = (i + i2) - 1;
        int i4 = 0;
        int i5 = (i2 * 3) / 4;
        byte[] bArr2 = new byte[i5];
        while (mutableInt.intValue() <= i3) {
            byte bD = d(bArr, mutableInt, i3);
            byte bD2 = d(bArr, mutableInt, i3);
            byte bD3 = d(bArr, mutableInt, i3);
            byte bD4 = d(bArr, mutableInt, i3);
            if (-2 != bD2) {
                bArr2[i4] = (byte) ((bD << 2) | (bD2 >>> 4));
                i4++;
            }
            if (-2 != bD3) {
                bArr2[i4] = (byte) (((bD2 & 15) << 4) | (bD3 >>> 2));
                i4++;
            }
            if (-2 != bD4) {
                bArr2[i4] = (byte) (((bD3 & 3) << 6) | bD4);
                i4++;
            }
        }
        return i4 == i5 ? bArr2 : (byte[]) cn.hutool.core.util.a.d(bArr2, new byte[i4], i4);
    }

    private static byte d(byte[] bArr, MutableInt mutableInt, int i) {
        byte b2;
        while (mutableInt.intValue() <= i) {
            byte b3 = bArr[mutableInt.intValue()];
            mutableInt.increment();
            if (b3 > -1 && (b2 = f2873b[b3]) > -1) {
                return b2;
            }
        }
        return (byte) -2;
    }
}
