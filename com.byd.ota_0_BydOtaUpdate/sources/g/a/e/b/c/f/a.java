package g.a.e.b.c.f;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static byte[] a(short[] sArr) {
        byte[] bArr = new byte[sArr.length];
        for (int i2 = 0; i2 < sArr.length; i2++) {
            bArr[i2] = (byte) sArr[i2];
        }
        return bArr;
    }

    public static short[] b(byte[] bArr) {
        short[] sArr = new short[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sArr[i2] = (short) (bArr[i2] & 255);
        }
        return sArr;
    }

    public static byte[][] c(short[][] sArr) {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) byte.class, sArr.length, sArr[0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr[0].length; i3++) {
                bArr[i2][i3] = (byte) sArr[i2][i3];
            }
        }
        return bArr;
    }

    public static short[][] d(byte[][] bArr) {
        short[][] sArr = (short[][]) Array.newInstance((Class<?>) short.class, bArr.length, bArr[0].length);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                sArr[i2][i3] = (short) (bArr[i2][i3] & 255);
            }
        }
        return sArr;
    }

    public static byte[][][] e(short[][][] sArr) {
        byte[][][] bArr = (byte[][][]) Array.newInstance((Class<?>) byte.class, sArr.length, sArr[0].length, sArr[0][0].length);
        for (int i2 = 0; i2 < sArr.length; i2++) {
            for (int i3 = 0; i3 < sArr[0].length; i3++) {
                for (int i4 = 0; i4 < sArr[0][0].length; i4++) {
                    bArr[i2][i3][i4] = (byte) sArr[i2][i3][i4];
                }
            }
        }
        return bArr;
    }

    public static short[][][] f(byte[][][] bArr) {
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) short.class, bArr.length, bArr[0].length, bArr[0][0].length);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                for (int i4 = 0; i4 < bArr[0][0].length; i4++) {
                    sArr[i2][i3][i4] = (short) (bArr[i2][i3][i4] & 255);
                }
            }
        }
        return sArr;
    }

    public static int[] g(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        return iArr;
    }

    public static byte[] h(int[] iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            bArr[i2] = (byte) iArr[i2];
        }
        return bArr;
    }

    public static boolean i(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            z &= sArr[length] == sArr2[length];
        }
        return z;
    }

    public static boolean j(short[][] sArr, short[][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean zI = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            zI &= i(sArr[length], sArr2[length]);
        }
        return zI;
    }

    public static boolean k(short[][][] sArr, short[][][] sArr2) {
        if (sArr.length != sArr2.length) {
            return false;
        }
        boolean zJ = true;
        for (int length = sArr.length - 1; length >= 0; length--) {
            zJ &= j(sArr[length], sArr2[length]);
        }
        return zJ;
    }
}
