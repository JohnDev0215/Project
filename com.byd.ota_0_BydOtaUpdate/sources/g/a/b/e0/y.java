package g.a.b.e0;

import g.a.b.d0.t0;
import g.a.b.j0.s0;

/* JADX INFO: loaded from: classes.dex */
public class y {
    public static void a(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2) {
        System.arraycopy(iArr, iArr.length - 16, iArr2, 0, 16);
        int length = iArr.length >>> 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = i2 * 2; i5 > 0; i5--) {
            h(iArr2, iArr, i3, iArr3);
            t0.i(8, iArr3, iArr2);
            System.arraycopy(iArr2, 0, iArr4, i4, 16);
            i4 = (length + i3) - i4;
            i3 += 16;
        }
        System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
    }

    public static void b(byte[] bArr) {
        if (bArr != null) {
            g.a.f.a.u(bArr, (byte) 0);
        }
    }

    public static void c(int[] iArr) {
        if (iArr != null) {
            g.a.f.a.v(iArr, 0);
        }
    }

    public static void d(int[][] iArr) {
        for (int[] iArr2 : iArr) {
            c(iArr2);
        }
    }

    public static byte[] e(byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5) {
        int i6 = i3 * 128;
        byte[] bArrG = g(bArr, bArr2, i4 * i6);
        int[] iArr = null;
        try {
            int length = bArrG.length >>> 2;
            iArr = new int[length];
            g.a.f.f.k(bArrG, 0, iArr);
            int i7 = i6 >>> 2;
            for (int i8 = 0; i8 < length; i8 += i7) {
                f(iArr, i8, i2, i3);
            }
            g.a.f.f.h(iArr, bArrG, 0);
            return g(bArr, bArrG, i5);
        } finally {
            b(bArrG);
            c(iArr);
        }
    }

    public static void f(int[] iArr, int i2, int i3, int i4) {
        int i5 = i4 * 32;
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[i5];
        int[] iArr5 = new int[i5];
        int[][] iArr6 = new int[i3][];
        try {
            System.arraycopy(iArr, i2, iArr5, 0, i5);
            for (int i6 = 0; i6 < i3; i6++) {
                iArr6[i6] = g.a.f.a.i(iArr5);
                a(iArr5, iArr2, iArr3, iArr4, i4);
            }
            int i7 = i3 - 1;
            for (int i8 = 0; i8 < i3; i8++) {
                h(iArr5, iArr6[iArr5[i5 - 16] & i7], 0, iArr5);
                a(iArr5, iArr2, iArr3, iArr4, i4);
            }
            System.arraycopy(iArr5, 0, iArr, i2, i5);
            d(iArr6);
            d(new int[][]{iArr5, iArr2, iArr3, iArr4});
        } catch (Throwable th) {
            d(iArr6);
            d(new int[][]{iArr5, iArr2, iArr3, iArr4});
            throw th;
        }
    }

    public static byte[] g(byte[] bArr, byte[] bArr2, int i2) {
        v vVar = new v(new g.a.b.a0.u());
        vVar.g(bArr, bArr2, 1);
        return ((s0) vVar.d(i2 * 8)).a();
    }

    public static void h(int[] iArr, int[] iArr2, int i2, int[] iArr3) {
        for (int length = iArr3.length - 1; length >= 0; length--) {
            iArr3[length] = iArr[length] ^ iArr2[i2 + length];
        }
    }

    public static byte[] i(byte[] bArr, byte[] bArr2, int i2, int i3, int i4, int i5) {
        if (bArr == null) {
            throw new IllegalArgumentException("Passphrase P must be provided.");
        }
        if (bArr2 == null) {
            throw new IllegalArgumentException("Salt S must be provided.");
        }
        if (i2 <= 1 || !j(i2)) {
            throw new IllegalArgumentException("Cost parameter N must be > 1 and a power of 2");
        }
        if (i3 == 1 && i2 >= 65536) {
            throw new IllegalArgumentException("Cost parameter N must be > 1 and < 65536.");
        }
        if (i3 < 1) {
            throw new IllegalArgumentException("Block size r must be >= 1.");
        }
        int i6 = Integer.MAX_VALUE / ((i3 * 128) * 8);
        if (i4 >= 1 && i4 <= i6) {
            if (i5 >= 1) {
                return e(bArr, bArr2, i2, i3, i4, i5);
            }
            throw new IllegalArgumentException("Generated key length dkLen must be >= 1.");
        }
        throw new IllegalArgumentException("Parallelisation parameter p must be >= 1 and <= " + i6 + " (based on block size r of " + i3 + ")");
    }

    public static boolean j(int i2) {
        return (i2 & (i2 + (-1))) == 0;
    }
}
