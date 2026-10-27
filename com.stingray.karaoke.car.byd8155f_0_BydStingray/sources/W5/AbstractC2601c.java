package w5;

import K.L0;

/* JADX INFO: renamed from: w5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2601c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f23329a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f23330b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    public static final int[][] c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    public static final int[][] d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    public static int a(L0 l02, boolean z6) {
        int i7 = l02.f3180m;
        int i10 = l02.f3181n;
        int i11 = z6 ? i10 : i7;
        if (!z6) {
            i7 = i10;
        }
        int i12 = 0;
        for (int i13 = 0; i13 < i11; i13++) {
            byte b10 = -1;
            int i14 = 0;
            for (int i15 = 0; i15 < i7; i15++) {
                byte[][] bArr = (byte[][]) l02.o;
                byte b11 = z6 ? bArr[i13][i15] : bArr[i15][i13];
                if (b11 == b10) {
                    i14++;
                } else {
                    if (i14 >= 5) {
                        i12 += i14 - 2;
                    }
                    i14 = 1;
                    b10 = b11;
                }
            }
            if (i14 >= 5) {
                i12 = (i14 - 2) + i12;
            }
        }
        return i12;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x025c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(t5.C2436a r27, int r28, v5.C2552b r29, int r30, K.L0 r31) throws I7.a {
        /*
            Method dump skipped, instruction units count: 760
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w5.AbstractC2601c.b(t5.a, int, v5.b, int, K.L0):void");
    }

    public static int c(int i7, int i10) {
        if (i10 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i10);
        int i11 = 32 - iNumberOfLeadingZeros;
        int iNumberOfLeadingZeros2 = i7 << (31 - iNumberOfLeadingZeros);
        while (32 - Integer.numberOfLeadingZeros(iNumberOfLeadingZeros2) >= i11) {
            iNumberOfLeadingZeros2 ^= i10 << ((32 - Integer.numberOfLeadingZeros(iNumberOfLeadingZeros2)) - i11);
        }
        return iNumberOfLeadingZeros2;
    }

    public static void d(int i7, int i10, L0 l02) throws I7.a {
        for (int i11 = 0; i11 < 8; i11++) {
            int i12 = i7 + i11;
            if (!g(l02.g(i12, i10))) {
                throw new I7.a();
            }
            l02.h(i12, i10, 0);
        }
    }

    public static void e(int i7, int i10, L0 l02) {
        for (int i11 = 0; i11 < 7; i11++) {
            int[] iArr = f23329a[i11];
            for (int i12 = 0; i12 < 7; i12++) {
                l02.h(i7 + i12, i10 + i11, iArr[i12]);
            }
        }
    }

    public static void f(int i7, int i10, L0 l02) throws I7.a {
        for (int i11 = 0; i11 < 7; i11++) {
            int i12 = i10 + i11;
            if (!g(l02.g(i7, i12))) {
                throw new I7.a();
            }
            l02.h(i7, i12, 0);
        }
    }

    public static boolean g(int i7) {
        return i7 == -1;
    }
}
