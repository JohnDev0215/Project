package com.google.zxing.pdf417.decoder;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: PDF417CodewordDecoder.java */
/* JADX INFO: loaded from: classes.dex */
final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final float[][] f4471a = (float[][]) Array.newInstance((Class<?>) float.class, com.google.zxing.o.a.f4382b.length, 8);

    static {
        int i;
        int i2 = 0;
        while (true) {
            int[] iArr = com.google.zxing.o.a.f4382b;
            if (i2 >= iArr.length) {
                return;
            }
            int i3 = iArr[i2];
            int i4 = i3 & 1;
            int i5 = 0;
            while (i5 < 8) {
                float f2 = 0.0f;
                while (true) {
                    i = i3 & 1;
                    if (i == i4) {
                        f2 += 1.0f;
                        i3 >>= 1;
                    }
                }
                f4471a[i2][(8 - i5) - 1] = f2 / 17.0f;
                i5++;
                i4 = i;
            }
            i2++;
        }
    }

    private static int a(int[] iArr) {
        long j = 0;
        for (int i = 0; i < iArr.length; i++) {
            for (int i2 = 0; i2 < iArr[i]; i2++) {
                int i3 = 1;
                long j2 = j << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                j = j2 | ((long) i3);
            }
        }
        return (int) j;
    }

    private static int b(int[] iArr) {
        int iD = com.google.zxing.common.l.a.d(iArr);
        float[] fArr = new float[8];
        if (iD > 1) {
            for (int i = 0; i < 8; i++) {
                fArr[i] = iArr[i] / iD;
            }
        }
        float f2 = Float.MAX_VALUE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            float[][] fArr2 = f4471a;
            if (i3 >= fArr2.length) {
                return i2;
            }
            float f3 = 0.0f;
            float[] fArr3 = fArr2[i3];
            for (int i4 = 0; i4 < 8; i4++) {
                float f4 = fArr3[i4] - fArr[i4];
                f3 += f4 * f4;
                if (f3 >= f2) {
                    break;
                }
            }
            if (f3 < f2) {
                i2 = com.google.zxing.o.a.f4382b[i3];
                f2 = f3;
            }
            i3++;
        }
    }

    private static int c(int[] iArr) {
        int iA = a(iArr);
        if (com.google.zxing.o.a.a(iA) == -1) {
            return -1;
        }
        return iA;
    }

    static int d(int[] iArr) {
        int iC = c(e(iArr));
        return iC != -1 ? iC : b(iArr);
    }

    private static int[] e(int[] iArr) {
        float fD = com.google.zxing.common.l.a.d(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (iArr[i2] + i <= (fD / 34.0f) + ((i3 * fD) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }
}
