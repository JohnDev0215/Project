package g.a.d.d;

import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static int a(int i2) {
        int i3 = 0;
        while ((i2 & 1) == 0) {
            i2 >>>= 1;
            i3++;
        }
        return i3;
    }

    public static void b(int[] iArr, int i2, int[] iArr2, int[] iArr3) {
        if (i2 < 0) {
            m.a(iArr.length, iArr2, iArr, iArr3);
        } else {
            System.arraycopy(iArr2, 0, iArr3, 0, iArr.length);
        }
    }

    public static int c(int[] iArr, int[] iArr2, int i2, int[] iArr3, int i3) {
        int length = iArr.length;
        int i4 = 0;
        while (iArr2[0] == 0) {
            m.C(i2, iArr2, 0);
            i4 += 32;
        }
        int iA = a(iArr2[0]);
        if (iA > 0) {
            m.A(i2, iArr2, iA, 0);
            i4 += iA;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if ((iArr3[0] & 1) != 0) {
                i3 += i3 < 0 ? m.e(length, iArr, iArr3) : m.N(length, iArr, iArr3);
            }
            m.z(length, iArr3, i3);
        }
        return i3;
    }

    public static void d(int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        if (m.w(length, iArr2)) {
            throw new IllegalArgumentException("'x' cannot be 0");
        }
        int iC = 0;
        if (m.v(length, iArr2)) {
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            return;
        }
        int[] iArrI = m.i(length, iArr2);
        int[] iArrJ = m.j(length);
        iArrJ[0] = 1;
        int iC2 = (1 & iArrI[0]) == 0 ? c(iArr, iArrI, length, iArrJ, 0) : 0;
        if (m.v(length, iArrI)) {
            b(iArr, iC2, iArrJ, iArr3);
            return;
        }
        int[] iArrI2 = m.i(length, iArr);
        int[] iArrJ2 = m.j(length);
        int i2 = length;
        while (true) {
            int i3 = i2 - 1;
            if (iArrI[i3] == 0 && iArrI2[i3] == 0) {
                i2--;
            } else if (m.q(i2, iArrI, iArrI2)) {
                m.N(i2, iArrI2, iArrI);
                iC2 = c(iArr, iArrI, i2, iArrJ, iC2 + (m.N(length, iArrJ2, iArrJ) - iC));
                if (m.v(i2, iArrI)) {
                    b(iArr, iC2, iArrJ, iArr3);
                    return;
                }
            } else {
                m.N(i2, iArrI, iArrI2);
                iC = c(iArr, iArrI2, i2, iArrJ2, iC + (m.N(length, iArrJ, iArrJ2) - iC2));
                if (m.v(i2, iArrI2)) {
                    b(iArr, iC, iArrJ2, iArr3);
                    return;
                }
            }
        }
    }

    public static int[] e(int[] iArr) {
        int length = iArr.length;
        Random random = new Random();
        int[] iArrJ = m.j(length);
        int i2 = length - 1;
        int i3 = iArr[i2];
        int i4 = i3 | (i3 >>> 1);
        int i5 = i4 | (i4 >>> 2);
        int i6 = i5 | (i5 >>> 4);
        int i7 = i6 | (i6 >>> 8);
        int i8 = i7 | (i7 >>> 16);
        do {
            for (int i9 = 0; i9 != length; i9++) {
                iArrJ[i9] = random.nextInt();
            }
            iArrJ[i2] = iArrJ[i2] & i8;
        } while (m.q(length, iArrJ, iArr));
        return iArrJ;
    }
}
