package com.google.zxing.n.r;

import com.google.zxing.NotFoundException;
import com.google.zxing.n.k;

/* JADX INFO: compiled from: AbstractRSSReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f4358b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int[] f4361e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int[] f4362f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int[] f4357a = new int[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float[] f4359c = new float[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f4360d = new float[4];

    protected a() {
        int[] iArr = new int[8];
        this.f4358b = iArr;
        this.f4361e = new int[iArr.length / 2];
        this.f4362f = new int[iArr.length / 2];
    }

    protected static void g(int[] iArr, float[] fArr) {
        int i = 0;
        float f2 = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f2) {
                f2 = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    protected static void n(int[] iArr, float[] fArr) {
        int i = 0;
        float f2 = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f2) {
                f2 = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    protected static boolean o(int[] iArr) {
        float f2 = (iArr[0] + iArr[1]) / ((iArr[2] + r1) + iArr[3]);
        if (f2 >= 0.7916667f && f2 <= 0.89285713f) {
            int i = Integer.MAX_VALUE;
            int i2 = Integer.MIN_VALUE;
            for (int i3 : iArr) {
                if (i3 > i2) {
                    i2 = i3;
                }
                if (i3 < i) {
                    i = i3;
                }
            }
            if (i2 < i * 10) {
                return true;
            }
        }
        return false;
    }

    protected static int p(int[] iArr, int[][] iArr2) throws NotFoundException {
        for (int i = 0; i < iArr2.length; i++) {
            if (k.d(iArr, iArr2[i], 0.45f) < 0.2f) {
                return i;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected final int[] h() {
        return this.f4358b;
    }

    protected final int[] i() {
        return this.f4357a;
    }

    protected final int[] j() {
        return this.f4362f;
    }

    protected final float[] k() {
        return this.f4360d;
    }

    protected final int[] l() {
        return this.f4361e;
    }

    protected final float[] m() {
        return this.f4359c;
    }
}
