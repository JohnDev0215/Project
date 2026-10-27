package g.a.f;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static int a(byte[] bArr, int i2) {
        int i3 = bArr[i2] << 24;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 16);
        int i6 = i4 + 1;
        return (bArr[i6 + 1] & 255) | i5 | ((bArr[i6] & 255) << 8);
    }

    public static long b(byte[] bArr, int i2) {
        int iA = a(bArr, i2);
        return (((long) a(bArr, i2 + 4)) & 4294967295L) | ((((long) iA) & 4294967295L) << 32);
    }

    public static void c(byte[] bArr, int i2, long[] jArr) {
        for (int i3 = 0; i3 < jArr.length; i3++) {
            jArr[i3] = b(bArr, i2);
            i2 += 8;
        }
    }

    public static void d(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 24);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i2 >>> 16);
        int i5 = i4 + 1;
        bArr[i5] = (byte) (i2 >>> 8);
        bArr[i5 + 1] = (byte) i2;
    }

    public static void e(int[] iArr, byte[] bArr, int i2) {
        for (int i3 : iArr) {
            d(i3, bArr, i2);
            i2 += 4;
        }
    }

    public static byte[] f(int i2) {
        byte[] bArr = new byte[4];
        d(i2, bArr, 0);
        return bArr;
    }

    public static void g(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i5 = i4 + 1;
        bArr[i5] = (byte) (i2 >>> 16);
        bArr[i5 + 1] = (byte) (i2 >>> 24);
    }

    public static void h(int[] iArr, byte[] bArr, int i2) {
        for (int i3 : iArr) {
            g(i3, bArr, i2);
            i2 += 4;
        }
    }

    public static byte[] i(int i2) {
        byte[] bArr = new byte[4];
        g(i2, bArr, 0);
        return bArr;
    }

    public static int j(byte[] bArr, int i2) {
        int i3 = bArr[i2] & 255;
        int i4 = i2 + 1;
        int i5 = i3 | ((bArr[i4] & 255) << 8);
        int i6 = i4 + 1;
        return (bArr[i6 + 1] << 24) | i5 | ((bArr[i6] & 255) << 16);
    }

    public static void k(byte[] bArr, int i2, int[] iArr) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            iArr[i3] = j(bArr, i2);
            i2 += 4;
        }
    }

    public static void l(byte[] bArr, int i2, int[] iArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i3 + i5] = j(bArr, i2);
            i2 += 4;
        }
    }

    public static int[] m(byte[] bArr, int i2, int i3) {
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i4] = j(bArr, i2);
            i2 += 4;
        }
        return iArr;
    }

    public static long n(byte[] bArr, int i2) {
        return ((((long) j(bArr, i2 + 4)) & 4294967295L) << 32) | (((long) j(bArr, i2)) & 4294967295L);
    }

    public static void o(byte[] bArr, int i2, long[] jArr) {
        for (int i3 = 0; i3 < jArr.length; i3++) {
            jArr[i3] = n(bArr, i2);
            i2 += 8;
        }
    }

    public static short p(byte[] bArr, int i2) {
        return (short) (((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255));
    }

    public static void q(long j, byte[] bArr, int i2) {
        d((int) (j >>> 32), bArr, i2);
        d((int) (j & 4294967295L), bArr, i2 + 4);
    }

    public static void r(long[] jArr, byte[] bArr, int i2) {
        for (long j : jArr) {
            q(j, bArr, i2);
            i2 += 8;
        }
    }

    public static byte[] s(long j) {
        byte[] bArr = new byte[8];
        q(j, bArr, 0);
        return bArr;
    }

    public static void t(long j, byte[] bArr, int i2) {
        g((int) (4294967295L & j), bArr, i2);
        g((int) (j >>> 32), bArr, i2 + 4);
    }

    public static void u(long[] jArr, int i2, int i3, byte[] bArr, int i4) {
        for (int i5 = 0; i5 < i3; i5++) {
            t(jArr[i2 + i5], bArr, i4);
            i4 += 8;
        }
    }

    public static void v(long[] jArr, byte[] bArr, int i2) {
        for (long j : jArr) {
            t(j, bArr, i2);
            i2 += 8;
        }
    }

    public static byte[] w(long j) {
        byte[] bArr = new byte[8];
        t(j, bArr, 0);
        return bArr;
    }

    public static byte[] x(long[] jArr) {
        byte[] bArr = new byte[jArr.length * 8];
        v(jArr, bArr, 0);
        return bArr;
    }

    public static void y(short s, byte[] bArr, int i2) {
        bArr[i2] = (byte) s;
        bArr[i2 + 1] = (byte) (s >>> 8);
    }
}
