package g.a.d.d;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {
    public static int A(int i2, int[] iArr, int i3, int i4) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i4 << (-i3);
            }
            int i5 = iArr[i2];
            iArr[i2] = (i4 << (-i3)) | (i5 >>> i3);
            i4 = i5;
        }
    }

    public static int B(int i2, int[] iArr, int i3, int i4, int i5, int[] iArr2, int i6) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i5 << (-i4);
            }
            int i7 = iArr[i3 + i2];
            iArr2[i6 + i2] = (i5 << (-i4)) | (i7 >>> i4);
            i5 = i7;
        }
    }

    public static int C(int i2, int[] iArr, int i3) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3;
            }
            int i4 = iArr[i2];
            iArr[i2] = i3;
            i3 = i4;
        }
    }

    public static int D(int i2, int[] iArr, int i3, int i4, int[] iArr2, int i5) {
        int i6 = 0;
        while (i6 < i2) {
            int i7 = iArr[i3 + i6];
            iArr2[i5 + i6] = (i4 >>> 31) | (i7 << 1);
            i6++;
            i4 = i7;
        }
        return i4 >>> 31;
    }

    public static int E(int i2, int[] iArr, int i3, int[] iArr2) {
        int i4 = 0;
        while (i4 < i2) {
            int i5 = iArr[i4];
            iArr2[i4] = (i3 >>> 31) | (i5 << 1);
            i4++;
            i3 = i5;
        }
        return i3 >>> 31;
    }

    public static long F(int i2, long[] jArr, int i3, long j, long[] jArr2, int i4) {
        int i5 = 0;
        while (i5 < i2) {
            long j2 = jArr[i3 + i5];
            jArr2[i4 + i5] = (j >>> 63) | (j2 << 1);
            i5++;
            j = j2;
        }
        return j >>> 63;
    }

    public static int G(int i2, int[] iArr, int i3, int i4) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = iArr[i5];
            iArr[i5] = (i4 >>> (-i3)) | (i6 << i3);
            i5++;
            i4 = i6;
        }
        return i4 >>> (-i3);
    }

    public static int H(int i2, int[] iArr, int i3, int i4, int[] iArr2) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = iArr[i5];
            iArr2[i5] = (i4 >>> (-i3)) | (i6 << i3);
            i5++;
            i4 = i6;
        }
        return i4 >>> (-i3);
    }

    public static long I(int i2, long[] jArr, int i3, int i4, long j) {
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i3 + i5;
            long j2 = jArr[i6];
            jArr[i6] = (j >>> (-i4)) | (j2 << i4);
            i5++;
            j = j2;
        }
        return j >>> (-i4);
    }

    public static long J(int i2, long[] jArr, int i3, int i4, long j, long[] jArr2, int i5) {
        int i6 = 0;
        while (i6 < i2) {
            long j2 = jArr[i3 + i6];
            jArr2[i5 + i6] = (j >>> (-i4)) | (j2 << i4);
            i6++;
            j = j2;
        }
        return j >>> (-i4);
    }

    public static int K(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = j + ((((long) iArr[i3]) & 4294967295L) - (4294967295L & ((long) iArr2[i3])));
            iArr3[i3] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int L(int i2, int i3, int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) - (((long) i3) & 4294967295L);
        iArr[0] = (int) j;
        long j2 = (j >> 32) + ((4294967295L & ((long) iArr[1])) - 1);
        iArr[1] = (int) j2;
        if ((j2 >> 32) == 0) {
            return 0;
        }
        return m(i2, iArr, 2);
    }

    public static int M(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i4 + i5;
            long j2 = j + ((((long) iArr2[i6]) & 4294967295L) - (4294967295L & ((long) iArr[i3 + i5])));
            iArr2[i6] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int N(int i2, int[] iArr, int[] iArr2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = j + ((((long) iArr2[i3]) & 4294967295L) - (4294967295L & ((long) iArr[i3])));
            iArr2[i3] = (int) j2;
            j = j2 >> 32;
        }
        return (int) j;
    }

    public static int O(int i2, int i3, int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) - (4294967295L & ((long) i3));
        iArr[0] = (int) j;
        if ((j >> 32) == 0) {
            return 0;
        }
        return m(i2, iArr, 1);
    }

    public static BigInteger P(int i2, int[] iArr) {
        byte[] bArr = new byte[i2 << 2];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3];
            if (i4 != 0) {
                g.a.f.f.d(i4, bArr, ((i2 - 1) - i3) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void Q(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = 0;
        }
    }

    public static void R(int i2, long[] jArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i3] = 0;
        }
    }

    public static int a(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = j + (((long) iArr[i3]) & 4294967295L) + (4294967295L & ((long) iArr2[i3]));
            iArr3[i3] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int b(int i2, int i3, int[] iArr) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) i3) & 4294967295L);
        iArr[0] = (int) j;
        long j2 = (j >>> 32) + (4294967295L & ((long) iArr[1])) + 1;
        iArr[1] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return t(i2, iArr, 2);
    }

    public static int c(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = j + (((long) iArr[i3]) & 4294967295L) + (((long) iArr2[i3]) & 4294967295L) + (4294967295L & ((long) iArr3[i3]));
            iArr3[i3] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int d(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        long j = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i4 + i5;
            long j2 = j + (((long) iArr[i3 + i5]) & 4294967295L) + (4294967295L & ((long) iArr2[i6]));
            iArr2[i6] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int e(int i2, int[] iArr, int[] iArr2) {
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = j + (((long) iArr[i3]) & 4294967295L) + (4294967295L & ((long) iArr2[i3]));
            iArr2[i3] = (int) j2;
            j = j2 >>> 32;
        }
        return (int) j;
    }

    public static int f(int i2, int i3, int[] iArr, int i4) {
        long j = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[i4]));
        iArr[i4] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return t(i2, iArr, i4 + 1);
    }

    public static int g(int i2, int i3, int[] iArr) {
        long j = (((long) i3) & 4294967295L) + (4294967295L & ((long) iArr[0]));
        iArr[0] = (int) j;
        if ((j >>> 32) == 0) {
            return 0;
        }
        return t(i2, iArr, 1);
    }

    public static void h(int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        System.arraycopy(iArr, i3, iArr2, i4, i2);
    }

    public static int[] i(int i2, int[] iArr) {
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }

    public static int[] j(int i2) {
        return new int[i2];
    }

    public static long[] k(int i2) {
        return new long[i2];
    }

    public static int l(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3] - 1;
            iArr[i3] = i4;
            if (i4 != -1) {
                return 0;
            }
        }
        return -1;
    }

    public static int m(int i2, int[] iArr, int i3) {
        while (i3 < i2) {
            int i4 = iArr[i3] - 1;
            iArr[i3] = i4;
            if (i4 != -1) {
                return 0;
            }
            i3++;
        }
        return -1;
    }

    public static boolean n(int i2, int[] iArr, int[] iArr2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (iArr[i3] != iArr2[i3]) {
                return false;
            }
        }
        return true;
    }

    public static int[] o(int i2, BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
            throw new IllegalArgumentException();
        }
        int[] iArrJ = j((i2 + 31) >> 5);
        int i3 = 0;
        while (bigInteger.signum() != 0) {
            iArrJ[i3] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i3++;
        }
        return iArrJ;
    }

    public static int p(int[] iArr, int i2) {
        int i3;
        if (i2 == 0) {
            i3 = iArr[0];
        } else {
            int i4 = i2 >> 5;
            if (i4 < 0 || i4 >= iArr.length) {
                return 0;
            }
            i3 = iArr[i4] >>> (i2 & 31);
        }
        return i3 & 1;
    }

    public static boolean q(int i2, int[] iArr, int[] iArr2) {
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            int i4 = iArr[i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static int r(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = iArr[i3] + 1;
            iArr[i3] = i4;
            if (i4 != 0) {
                return 0;
            }
        }
        return 1;
    }

    public static int s(int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr2[i3] = i4;
            i3++;
            if (i4 != 0) {
                while (i3 < i2) {
                    iArr2[i3] = iArr[i3];
                    i3++;
                }
                return 0;
            }
        }
        return 1;
    }

    public static int t(int i2, int[] iArr, int i3) {
        while (i3 < i2) {
            int i4 = iArr[i3] + 1;
            iArr[i3] = i4;
            if (i4 != 0) {
                return 0;
            }
            i3++;
        }
        return 1;
    }

    public static int u(int i2, int[] iArr, int i3, int i4) {
        while (i4 < i2) {
            int i5 = i3 + i4;
            int i6 = iArr[i5] + 1;
            iArr[i5] = i6;
            if (i6 != 0) {
                return 0;
            }
            i4++;
        }
        return 1;
    }

    public static boolean v(int i2, int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i3 = 1; i3 < i2; i3++) {
            if (iArr[i3] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean w(int i2, int[] iArr) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (iArr[i3] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int x(int i2, int i3, int[] iArr, int i4, int[] iArr2, int[] iArr3, int i5) {
        long j = ((long) i3) & 4294967295L;
        long j2 = ((long) i4) & 4294967295L;
        long j3 = 0;
        int i6 = 0;
        do {
            int i7 = i5 + i6;
            long j4 = j3 + ((((long) iArr[i6]) & 4294967295L) * j) + ((((long) iArr2[i6]) & 4294967295L) * j2) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j4;
            j3 = j4 >>> 32;
            i6++;
        } while (i6 < i2);
        return (int) j3;
    }

    public static int y(int i2, int i3, int[] iArr, int i4, int[] iArr2, int i5) {
        long j = ((long) i3) & 4294967295L;
        long j2 = 0;
        int i6 = 0;
        do {
            int i7 = i5 + i6;
            long j3 = j2 + ((((long) iArr[i4 + i6]) & 4294967295L) * j) + (((long) iArr2[i7]) & 4294967295L);
            iArr2[i7] = (int) j3;
            j2 = j3 >>> 32;
            i6++;
        } while (i6 < i2);
        return (int) j2;
    }

    public static int z(int i2, int[] iArr, int i3) {
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3 << 31;
            }
            int i4 = iArr[i2];
            iArr[i2] = (i3 << 31) | (i4 >>> 1);
            i3 = i4;
        }
    }
}
