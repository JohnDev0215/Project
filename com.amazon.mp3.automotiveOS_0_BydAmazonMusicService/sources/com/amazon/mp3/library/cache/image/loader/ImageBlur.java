package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageBlur {
    public static Bitmap generateBoxBlurredBitmap(Bitmap source, int range, int repetitions) {
        int width = source.getWidth();
        int height = source.getHeight();
        if (width != height) {
            throw new IllegalArgumentException("Source bitmap must be square!");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int[] iArr = new int[width * height];
        source.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i = 0; i < repetitions; i++) {
            oneDimensionBlur(iArr, width, height, range);
        }
        transpose(iArr, width);
        for (int i2 = 0; i2 < repetitions; i2++) {
            oneDimensionBlur(iArr, width, height, range);
        }
        transpose(iArr, width);
        canvas.drawBitmap(iArr, 0, width, 0.0f, 0.0f, width, height, true, (Paint) null);
        return bitmapCreateBitmap;
    }

    private static void oneDimensionBlur(int[] pixels, int width, int height, int range) {
        int[] iArr = new int[width];
        for (int i = 0; i < height; i++) {
            int i2 = i * width;
            System.arraycopy(pixels, i2, iArr, 0, width);
            for (int i3 = 0; i3 < width; i3++) {
                int iMax = Math.max(0, i3 - range);
                int iMin = Math.min(i3 + range, width);
                int i4 = iMin - iMax;
                int iRed = 0;
                int iBlue = 0;
                int iGreen = 0;
                while (iMax < iMin) {
                    int i5 = iArr[iMax];
                    iRed += Color.red(i5);
                    iBlue += Color.blue(i5);
                    iGreen += Color.green(i5);
                    iMax++;
                }
                pixels[i2 + i3] = Color.rgb(iRed / i4, iGreen / i4, iBlue / i4);
            }
        }
    }

    private static void transpose(int[] pixels, int side) {
        int i = 0;
        while (i < side - 2) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < side - 1; i3++) {
                int i4 = (i3 * side) + i;
                int i5 = (i * side) + i3;
                int i6 = pixels[i4] + pixels[i5];
                pixels[i4] = i6;
                int i7 = i6 - pixels[i5];
                pixels[i5] = i7;
                pixels[i4] = pixels[i4] - i7;
            }
            i = i2;
        }
    }
}
