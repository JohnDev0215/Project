package g.a.f;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: g.a.f.a$a, reason: collision with other inner class name */
    public static class C0097a<T> implements Iterator<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final T[] f5721c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5722d = 0;

        public C0097a(T[] tArr) {
            this.f5721c = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f5722d < this.f5721c.length;
        }

        @Override // java.util.Iterator
        public T next() {
            int i2 = this.f5722d;
            T[] tArr = this.f5721c;
            if (i2 != tArr.length) {
                this.f5722d = i2 + 1;
                return tArr[i2];
            }
            throw new NoSuchElementException("Out of elements: " + this.f5722d);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove element from an Array.");
        }
    }

    public static int A(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ cArr[length];
        }
    }

    public static int B(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        int length = iArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ iArr[length];
        }
    }

    public static int C(int[] iArr, int i2, int i3) {
        if (iArr == null) {
            return 0;
        }
        int i4 = i3 + 1;
        while (true) {
            i3--;
            if (i3 < 0) {
                return i4;
            }
            i4 = (i4 * 257) ^ iArr[i2 + i3];
        }
    }

    public static int D(long[] jArr, int i2, int i3) {
        if (jArr == null) {
            return 0;
        }
        int i4 = i3 + 1;
        while (true) {
            i3--;
            if (i3 < 0) {
                return i4;
            }
            long j = jArr[i2 + i3];
            i4 = (((i4 * 257) ^ ((int) j)) * 257) ^ ((int) (j >>> 32));
        }
    }

    public static int E(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        int length = sArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ (sArr[length] & 255);
        }
    }

    public static int F(short[][] sArr) {
        int iE = 0;
        for (int i2 = 0; i2 != sArr.length; i2++) {
            iE = (iE * 257) + E(sArr[i2]);
        }
        return iE;
    }

    public static int G(short[][][] sArr) {
        int iF = 0;
        for (int i2 = 0; i2 != sArr.length; i2++) {
            iF = (iF * 257) + F(sArr[i2]);
        }
        return iF;
    }

    public static byte[] H(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i2 = 0;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        while (true) {
            length--;
            if (length < 0) {
                return bArr2;
            }
            bArr2[length] = bArr[i2];
            i2++;
        }
    }

    public static int[] I(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int i2 = 0;
        int length = iArr.length;
        int[] iArr2 = new int[length];
        while (true) {
            length--;
            if (length < 0) {
                return iArr2;
            }
            iArr2[length] = iArr[i2];
            i2++;
        }
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != bArr.length; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != cArr.length; i2++) {
            if (cArr[i2] != cArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return true;
        }
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != iArr.length; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(short[] sArr, short[] sArr2) {
        if (sArr == sArr2) {
            return true;
        }
        if (sArr == null || sArr2 == null || sArr.length != sArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 != sArr.length; i2++) {
            if (sArr[i2] != sArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static void e(byte[] bArr) {
        if (bArr != null) {
            for (int i2 = 0; i2 < bArr.length; i2++) {
                bArr[i2] = 0;
            }
        }
    }

    public static byte[] f(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[] g(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return null;
        }
        if (bArr2 == null || bArr2.length != bArr.length) {
            return f(bArr);
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    public static char[] h(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        char[] cArr2 = new char[cArr.length];
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        return cArr2;
    }

    public static int[] i(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    public static long[] j(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    public static long[] k(long[] jArr, long[] jArr2) {
        if (jArr == null) {
            return null;
        }
        if (jArr2 == null || jArr2.length != jArr.length) {
            return j(jArr);
        }
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        return jArr2;
    }

    public static short[] l(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        short[] sArr2 = new short[sArr.length];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    public static byte[] m(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return bArr2 != null ? f(bArr2) : f(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] n(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return bArr == null ? m(bArr2, bArr3) : bArr2 == null ? m(bArr, bArr3) : m(bArr, bArr2);
        }
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    public static byte[] o(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr == null || bArr2 == null || bArr3 == null || bArr4 == null) {
            return bArr4 == null ? n(bArr, bArr2, bArr3) : bArr3 == null ? n(bArr, bArr2, bArr4) : bArr2 == null ? n(bArr, bArr3, bArr4) : n(bArr2, bArr3, bArr4);
        }
        byte[] bArr5 = new byte[bArr.length + bArr2.length + bArr3.length + bArr4.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
        return bArr5;
    }

    public static byte[] p(byte[][] bArr) {
        int length = 0;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            length += bArr[i2].length;
        }
        byte[] bArr2 = new byte[length];
        int length2 = 0;
        for (int i3 = 0; i3 != bArr.length; i3++) {
            System.arraycopy(bArr[i3], 0, bArr2, length2, bArr[i3].length);
            length2 += bArr[i3].length;
        }
        return bArr2;
    }

    public static boolean q(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null) {
            return false;
        }
        if (bArr.length != bArr2.length) {
            return !q(bArr, bArr);
        }
        int i2 = 0;
        for (int i3 = 0; i3 != bArr.length; i3++) {
            i2 |= bArr[i3] ^ bArr2[i3];
        }
        return i2 == 0;
    }

    public static byte[] r(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        if (i2 >= bArr.length) {
            i2 = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    public static byte[] s(byte[] bArr, int i2, int i3) {
        int iY = y(i2, i3);
        byte[] bArr2 = new byte[iY];
        if (bArr.length - i2 < iY) {
            iY = bArr.length - i2;
        }
        System.arraycopy(bArr, i2, bArr2, 0, iY);
        return bArr2;
    }

    public static int[] t(int[] iArr, int i2, int i3) {
        int iY = y(i2, i3);
        int[] iArr2 = new int[iY];
        if (iArr.length - i2 < iY) {
            iY = iArr.length - i2;
        }
        System.arraycopy(iArr, i2, iArr2, 0, iY);
        return iArr2;
    }

    public static void u(byte[] bArr, byte b2) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = b2;
        }
    }

    public static void v(int[] iArr, int i2) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            iArr[i3] = i2;
        }
    }

    public static void w(long[] jArr, long j) {
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = j;
        }
    }

    public static void x(short[] sArr, short s) {
        for (int i2 = 0; i2 < sArr.length; i2++) {
            sArr[i2] = s;
        }
    }

    public static int y(int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 >= 0) {
            return i4;
        }
        StringBuffer stringBuffer = new StringBuffer(i2);
        stringBuffer.append(" > ");
        stringBuffer.append(i3);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static int z(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int i2 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i2;
            }
            i2 = (i2 * 257) ^ bArr[length];
        }
    }
}
