package com.android.inputmethod.keyboard.internal;

import android.util.Log;
import com.android.inputmethod.keyboard.internal.MatrixUtils;
import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothingUtils {
    private static final boolean DEBUG = false;
    private static final String TAG = "SmoothingUtils";

    private SmoothingUtils() {
    }

    public static void get3DParameters(final float[] xs, final float[] ys, final float[][] retval) throws MatrixUtils.MatrixOperationFailedException {
        int i = 0;
        if (retval.length != 4 || retval[0].length != 1) {
            Log.d(TAG, "--- invalid length of 3d retval " + retval.length + ", " + retval[0].length);
            return;
        }
        int length = xs.length;
        float[][] fArr = (float[][]) Array.newInstance((Class<?>) float.class, 4, 4);
        float[][] fArr2 = (float[][]) Array.newInstance((Class<?>) float.class, 4, 4);
        float[][] fArr3 = (float[][]) Array.newInstance((Class<?>) float.class, 4, length);
        float[][] fArr4 = (float[][]) Array.newInstance((Class<?>) float.class, length, 1);
        int i2 = 0;
        while (i2 < 4) {
            Arrays.fill(fArr[i2], 0.0f);
            int i3 = i;
            while (i3 < 4) {
                int i4 = i2 + i3;
                int i5 = i;
                while (i5 < length) {
                    float[] fArr5 = fArr[i2];
                    fArr5[i3] = fArr5[i3] + ((float) Math.pow(xs[i5], i4));
                    i5++;
                    fArr = fArr;
                }
                i3++;
                i = 0;
            }
            i2++;
            i = 0;
        }
        MatrixUtils.inverse(fArr, fArr2);
        int i6 = 0;
        while (i6 < 4) {
            for (int i7 = 0; i7 < length; i7++) {
                fArr3[i6][i7] = i6 == 0 ? 1.0f : fArr3[i6 - 1][i7] * xs[i7];
            }
            i6++;
        }
        for (int i8 = 0; i8 < length; i8++) {
            fArr4[i8][0] = ys[i8];
        }
        float[][] fArr6 = (float[][]) Array.newInstance((Class<?>) float.class, 4, length);
        MatrixUtils.multiply(fArr2, fArr3, fArr6);
        MatrixUtils.multiply(fArr6, fArr4, retval);
    }
}
