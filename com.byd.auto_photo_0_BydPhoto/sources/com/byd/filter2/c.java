package com.byd.filter2;

import android.opengl.Matrix;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static float[] a(float[] fArr, boolean z, boolean z2) {
        if (z || z2) {
            Matrix.scaleM(fArr, 0, z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f, 1.0f);
        }
        return fArr;
    }

    public static void b(float[] fArr, int i, int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || i2 <= 0 || i4 <= 0 || i5 <= 0) {
            return;
        }
        float[] fArr2 = new float[16];
        float[] fArr3 = new float[16];
        if (i == 0) {
            Matrix.orthoM(fArr2, 0, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 3.0f);
            Matrix.setLookAtM(fArr3, 0, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
            Matrix.multiplyMM(fArr, 0, fArr2, 0, fArr3, 0);
        }
        float f = i4 / i5;
        float f2 = i2 / i3;
        if (f2 > f) {
            if (i == 1) {
                Matrix.orthoM(fArr2, 0, (-f) / f2, f / f2, -1.0f, 1.0f, 1.0f, 3.0f);
            } else if (i == 2) {
                Matrix.orthoM(fArr2, 0, -1.0f, 1.0f, (-f2) / f, f2 / f, 1.0f, 3.0f);
            } else if (i == 3) {
                Matrix.orthoM(fArr2, 0, -1.0f, 1.0f, 1.0f - ((f2 * 2.0f) / f), 1.0f, 1.0f, 3.0f);
            } else if (i == 4) {
                Matrix.orthoM(fArr2, 0, -1.0f, 1.0f, -1.0f, ((f2 * 2.0f) / f) - 1.0f, 1.0f, 3.0f);
            }
        } else if (i == 1) {
            Matrix.orthoM(fArr2, 0, -1.0f, 1.0f, (-f2) / f, f2 / f, 1.0f, 3.0f);
        } else if (i == 2) {
            Matrix.orthoM(fArr2, 0, (-f) / f2, f / f2, -1.0f, 1.0f, 1.0f, 3.0f);
        } else if (i == 3) {
            Matrix.orthoM(fArr2, 0, -1.0f, ((f * 2.0f) / f2) - 1.0f, -1.0f, 1.0f, 1.0f, 3.0f);
        } else if (i == 4) {
            Matrix.orthoM(fArr2, 0, 1.0f - ((f * 2.0f) / f2), 1.0f, -1.0f, 1.0f, 1.0f, 3.0f);
        }
        Matrix.setLookAtM(fArr3, 0, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        Matrix.multiplyMM(fArr, 0, fArr2, 0, fArr3, 0);
    }

    public static float[] c() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static float[] d(float[] fArr, float f, float f2) {
        Matrix.scaleM(fArr, 0, f, f2, 1.0f);
        return fArr;
    }

    public static float[] e(float[] fArr, float f, float f2) {
        Matrix.translateM(fArr, 0, f, f2, 0.0f);
        return fArr;
    }
}
