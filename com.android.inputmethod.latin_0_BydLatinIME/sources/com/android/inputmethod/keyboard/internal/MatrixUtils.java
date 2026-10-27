package com.android.inputmethod.keyboard.internal;

import android.util.Log;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class MatrixUtils {
    static final String TAG = "MatrixUtils";

    public static class MatrixOperationFailedException extends Exception {
        private static final long serialVersionUID = 4384485606788583829L;

        public MatrixOperationFailedException(String msg) {
            super(msg);
            Log.d(MatrixUtils.TAG, msg);
        }
    }

    private static void findPivotAndSwapRow(final int row, final float[][] squareMatrix0, final float[][] squareMatrix1, final int size) {
        float fAbs = Math.abs(squareMatrix0[row][row]);
        int i = row;
        for (int i2 = row + 1; i2 < size; i2++) {
            if (fAbs < Math.abs(squareMatrix0[i2][row])) {
                fAbs = Math.abs(squareMatrix0[i2][row]);
                i = i2;
            }
        }
        if (i != row) {
            for (int i3 = 0; i3 < size; i3++) {
                float f = squareMatrix0[i][i3];
                squareMatrix0[i][i3] = squareMatrix0[row][i3];
                squareMatrix0[row][i3] = f;
                float f2 = squareMatrix1[i][i3];
                squareMatrix1[i][i3] = squareMatrix1[row][i3];
                squareMatrix1[row][i3] = f2;
            }
        }
    }

    private static void sweep(final int row, final float[][] squareMatrix0, final float[][] squareMatrix1, final int size) throws MatrixOperationFailedException {
        float f = squareMatrix0[row][row];
        if (f == 0.0f) {
            throw new MatrixOperationFailedException("Inverse failed. Invalid pivot");
        }
        for (int i = 0; i < size; i++) {
            float[] fArr = squareMatrix0[row];
            fArr[i] = fArr[i] / f;
            float[] fArr2 = squareMatrix1[row];
            fArr2[i] = fArr2[i] / f;
        }
        for (int i2 = 0; i2 < size; i2++) {
            float f2 = squareMatrix0[i2][row];
            if (i2 != row) {
                for (int i3 = row; i3 < size; i3++) {
                    float[] fArr3 = squareMatrix0[i2];
                    fArr3[i3] = fArr3[i3] - (squareMatrix0[row][i3] * f2);
                }
                for (int i4 = 0; i4 < size; i4++) {
                    float[] fArr4 = squareMatrix1[i2];
                    fArr4[i4] = fArr4[i4] - (squareMatrix1[row][i4] * f2);
                }
            }
        }
    }

    public static void inverse(final float[][] squareMatrix, final float[][] inverseMatrix) throws MatrixOperationFailedException {
        int length = squareMatrix.length;
        if (squareMatrix[0].length != length || inverseMatrix.length != length || inverseMatrix[0].length != length) {
            throw new MatrixOperationFailedException("--- invalid length. column should be 2 times larger than row.");
        }
        for (int i = 0; i < length; i++) {
            Arrays.fill(inverseMatrix[i], 0.0f);
            inverseMatrix[i][i] = 1.0f;
        }
        for (int i2 = 0; i2 < length; i2++) {
            findPivotAndSwapRow(i2, squareMatrix, inverseMatrix, length);
            sweep(i2, squareMatrix, inverseMatrix, length);
        }
    }

    public static void multiply(final float[][] m0, final float[][] m1, final float[][] retval) throws MatrixOperationFailedException {
        if (m0[0].length != m1.length) {
            throw new MatrixOperationFailedException("--- invalid length for multiply " + m0[0].length + ", " + m1.length);
        }
        int length = m0.length;
        int length2 = m0[0].length;
        int length3 = m1[0].length;
        if (retval.length != length || retval[0].length != length3) {
            throw new MatrixOperationFailedException("--- invalid length of retval " + retval.length + ", " + retval[0].length);
        }
        for (int i = 0; i < length; i++) {
            Arrays.fill(retval[i], 0.0f);
            for (int i2 = 0; i2 < length3; i2++) {
                for (int i3 = 0; i3 < length2; i3++) {
                    float[] fArr = retval[i];
                    fArr[i2] = fArr[i2] + (m0[i][i3] * m1[i3][i2]);
                }
            }
        }
    }

    public static void dump(final String title, final float[][] a) {
        int length = a[0].length;
        String str = TAG;
        Log.d(str, "Dump matrix: " + title);
        Log.d(str, "/*---------------------");
        StringBuilder sb = new StringBuilder();
        for (float[] fArr : a) {
            sb.setLength(0);
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%4f", Float.valueOf(fArr[i]))).append(' ');
            }
            Log.d(TAG, sb.toString());
        }
        Log.d(TAG, "---------------------*/");
    }
}
