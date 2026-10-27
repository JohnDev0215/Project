package g.a.d.d;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {
    public static void a(long[] jArr, int i2, long[] jArr2, int i3) {
        jArr2[i3 + 0] = jArr[i2 + 0];
        jArr2[i3 + 1] = jArr[i2 + 1];
        jArr2[i3 + 2] = jArr[i2 + 2];
        jArr2[i3 + 3] = jArr[i2 + 3];
        jArr2[i3 + 4] = jArr[i2 + 4];
        jArr2[i3 + 5] = jArr[i2 + 5];
        jArr2[i3 + 6] = jArr[i2 + 6];
        jArr2[i3 + 7] = jArr[i2 + 7];
        jArr2[i3 + 8] = jArr[i2 + 8];
    }

    public static long[] b() {
        return new long[9];
    }

    public static long[] c() {
        return new long[18];
    }

    public static boolean d(long[] jArr, long[] jArr2) {
        for (int i2 = 8; i2 >= 0; i2--) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static long[] e(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 576) {
            throw new IllegalArgumentException();
        }
        long[] jArrB = b();
        int i2 = 0;
        while (bigInteger.signum() != 0) {
            jArrB[i2] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
            i2++;
        }
        return jArrB;
    }

    public static boolean f(long[] jArr) {
        if (jArr[0] != 1) {
            return false;
        }
        for (int i2 = 1; i2 < 9; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean g(long[] jArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger h(long[] jArr) {
        byte[] bArr = new byte[72];
        for (int i2 = 0; i2 < 9; i2++) {
            long j = jArr[i2];
            if (j != 0) {
                g.a.f.f.q(j, bArr, (8 - i2) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }
}
