package com.amazon.music.picassoimageloader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes4.dex */
public final class ImageBlur {
    public static Bitmap generateBoxBlurredBitmap(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width != height) {
            throw new IllegalArgumentException("Source bitmap must be square!");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i3 = 0; i3 < i2; i3++) {
            oneDimensionBlur(iArr, width, height, i);
        }
        transpose(iArr, width);
        for (int i4 = 0; i4 < i2; i4++) {
            oneDimensionBlur(iArr, width, height, i);
        }
        transpose(iArr, width);
        canvas.drawBitmap(iArr, 0, width, 0.0f, 0.0f, width, height, true, (Paint) null);
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    private static void oneDimensionBlur(int[] iArr, int i, int i2, int i3) {
        int[] iArr2 = new int[i];
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * i;
            System.arraycopy(iArr, i5, iArr2, 0, i);
            for (int i6 = 0; i6 < i; i6++) {
                int iMax = Math.max(0, i6 - i3);
                int iMin = Math.min(i6 + i3, i);
                int i7 = iMin - iMax;
                int iRed = 0;
                int iBlue = 0;
                int iGreen = 0;
                while (iMax < iMin) {
                    int i8 = iArr2[iMax];
                    iRed += Color.red(i8);
                    iBlue += Color.blue(i8);
                    iGreen += Color.green(i8);
                    iMax++;
                }
                iArr[i5 + i6] = Color.rgb(iRed / i7, iGreen / i7, iBlue / i7);
            }
        }
    }

    private static void transpose(int[] iArr, int i) {
        int i2 = 0;
        while (i2 < i - 2) {
            int i3 = i2 + 1;
            for (int i4 = i3; i4 < i - 1; i4++) {
                int i5 = (i4 * i) + i2;
                int i6 = (i2 * i) + i4;
                int i7 = iArr[i5] + iArr[i6];
                iArr[i5] = i7;
                int i8 = i7 - iArr[i6];
                iArr[i6] = i8;
                iArr[i5] = iArr[i5] - i8;
            }
            i2 = i3;
        }
    }

    public static Bitmap generateBlurredBitmap(Bitmap bitmap, int i) {
        if (i < 1) {
            return null;
        }
        Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width * height;
        int[] iArr = new int[i2];
        bitmapCopy.getPixels(iArr, 0, width, 0, 0, width, height);
        bitmap.recycle();
        int i3 = (((i * 2) + 1) + 1) >> 1;
        int i4 = i3 * i3;
        int i5 = i4 * 256;
        int[] iArr2 = new int[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            iArr2[i6] = i6 / i4;
        }
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        int[] iArr5 = new int[i2];
        convolveVertically(i, width, height, iArr, iArr2, iArr3, iArr4, iArr5);
        convolveHorizontally(i, width, height, iArr, iArr2, iArr3, iArr4, iArr5);
        bitmapCopy.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCopy;
    }

    private static void setColor(int[] iArr, int i) {
        iArr[0] = Color.red(i);
        iArr[1] = Color.green(i);
        iArr[2] = Color.blue(i);
    }

    private static void convolveVertically(int i, int i2, int i3, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        int i4 = 1;
        int i5 = (i * 2) + 1;
        int i6 = 3;
        int[][] iArr6 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i5, 3);
        int[] iArr7 = new int[Math.max(i2, i3)];
        int i7 = i3;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            int[] iArr8 = new int[i6];
            int[] iArr9 = new int[i6];
            int[] iArr10 = new int[i6];
            for (int i11 = -i; i11 <= i; i11++) {
                int i12 = iArr[Math.min(i2 - 1, Math.max(i11, 0)) + i8];
                int[] iArr11 = iArr6[i11 + i];
                setColor(iArr11, i12);
                update(i11, (i + 1) - Math.abs(i11), iArr11, iArr10, iArr8, iArr9);
            }
            int i13 = i;
            int i14 = i8;
            int i15 = 0;
            while (i15 < i2) {
                iArr3[i14] = iArr2[iArr10[0]];
                iArr4[i14] = iArr2[iArr10[i4]];
                iArr5[i14] = iArr2[iArr10[2]];
                iArr10[0] = iArr10[0] - iArr9[0];
                iArr10[i4] = iArr10[i4] - iArr9[i4];
                iArr10[2] = iArr10[2] - iArr9[2];
                int[] iArr12 = iArr6[((i13 - i) + i5) % i5];
                iArr9[0] = iArr9[0] - iArr12[0];
                iArr9[i4] = iArr9[i4] - iArr12[i4];
                iArr9[2] = iArr9[2] - iArr12[2];
                if (i9 == 0) {
                    iArr7[i15] = Math.min(i15 + i + i4, i2 - 1);
                }
                setColor(iArr12, iArr[i10 + iArr7[i15]]);
                update(iArr12, iArr10, iArr8, iArr9, i13, i5, iArr6, true);
                i13 = (i13 + 1) % i5;
                i14++;
                i15++;
                iArr10 = iArr10;
                iArr8 = iArr8;
                i9 = i9;
                iArr7 = iArr7;
                i4 = 1;
            }
            i10 += i2;
            i9++;
            i7 = i3;
            i8 = i14;
            i4 = 1;
            i6 = 3;
        }
    }

    private static void convolveHorizontally(int i, int i2, int i3, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        int i4 = (i * 2) + 1;
        int i5 = 3;
        int[][] iArr6 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i4, 3);
        int[] iArr7 = new int[Math.max(i2, i3)];
        int i6 = 0;
        while (i6 < i2) {
            int[] iArr8 = new int[i5];
            int[] iArr9 = new int[i5];
            int[] iArr10 = new int[i5];
            int i7 = -i;
            int i8 = i7 * i2;
            int i9 = i7;
            while (i9 <= i) {
                int[] iArr11 = iArr6[i9 + i];
                int iMax = Math.max(0, i8) + i6;
                iArr11[0] = iArr3[iMax];
                iArr11[1] = iArr4[iMax];
                iArr11[2] = iArr5[iMax];
                int i10 = i8;
                int i11 = i9;
                int[] iArr12 = iArr10;
                int[] iArr13 = iArr9;
                int[] iArr14 = iArr8;
                update(i9, (i + 1) - Math.abs(i9), iArr11, iArr10, iArr8, iArr13);
                i8 = i11 < i3 + (-1) ? i10 + i2 : i10;
                i9 = i11 + 1;
                iArr10 = iArr12;
                iArr9 = iArr13;
                iArr8 = iArr14;
            }
            int[] iArr15 = iArr10;
            int[] iArr16 = iArr9;
            int[] iArr17 = iArr8;
            int i12 = i;
            int i13 = i6;
            int i14 = 0;
            while (i14 < i3) {
                iArr[i13] = ((-16777216) & iArr[i13]) | (iArr2[iArr15[0]] << 16) | (iArr2[iArr15[1]] << 8) | iArr2[iArr15[2]];
                iArr15[0] = iArr15[0] - iArr16[0];
                iArr15[1] = iArr15[1] - iArr16[1];
                iArr15[2] = iArr15[2] - iArr16[2];
                int[] iArr18 = iArr6[((i12 - i) + i4) % i4];
                iArr16[0] = iArr16[0] - iArr18[0];
                iArr16[1] = iArr16[1] - iArr18[1];
                iArr16[2] = iArr16[2] - iArr18[2];
                if (i6 == 0) {
                    iArr7[i14] = Math.min(i + 1 + i14, i3 - 1) * i2;
                }
                int i15 = iArr7[i14] + i6;
                iArr18[0] = iArr3[i15];
                iArr18[1] = iArr4[i15];
                iArr18[2] = iArr5[i15];
                update(iArr18, iArr15, iArr17, iArr16, i12, i4, iArr6, false);
                i12 = (i12 + 1) % i4;
                i13 += i2;
                i14++;
                i6 = i6;
                iArr7 = iArr7;
            }
            i6++;
            i5 = 3;
        }
    }

    private static void update(int i, int i2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        iArr2[0] = iArr2[0] + (iArr[0] * i2);
        iArr2[1] = iArr2[1] + (iArr[1] * i2);
        iArr2[2] = iArr2[2] + (iArr[2] * i2);
        if (i > 0) {
            iArr3[0] = iArr3[0] + iArr[0];
            iArr3[1] = iArr3[1] + iArr[1];
            iArr3[2] = iArr3[2] + iArr[2];
        } else {
            iArr4[0] = iArr4[0] + iArr[0];
            iArr4[1] = iArr4[1] + iArr[1];
            iArr4[2] = iArr4[2] + iArr[2];
        }
    }

    private static void update(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i, int i2, int[][] iArr5, boolean z) {
        int[] iArr6;
        int i3 = iArr3[0] + iArr[0];
        iArr3[0] = i3;
        int i4 = iArr3[1] + iArr[1];
        iArr3[1] = i4;
        int i5 = iArr3[2] + iArr[2];
        iArr3[2] = i5;
        iArr2[0] = iArr2[0] + i3;
        iArr2[1] = iArr2[1] + i4;
        iArr2[2] = iArr2[2] + i5;
        int i6 = (i + 1) % i2;
        if (z) {
            iArr6 = iArr5[i6];
        } else {
            iArr6 = iArr5[i6 % i2];
        }
        iArr4[0] = iArr4[0] + iArr6[0];
        iArr4[1] = iArr4[1] + iArr6[1];
        iArr4[2] = iArr4[2] + iArr6[2];
        iArr3[0] = iArr3[0] - iArr6[0];
        iArr3[1] = iArr3[1] - iArr6[1];
        iArr3[2] = iArr3[2] - iArr6[2];
    }
}
