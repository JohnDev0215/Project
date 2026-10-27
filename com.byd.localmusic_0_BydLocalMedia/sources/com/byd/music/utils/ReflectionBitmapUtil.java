package com.byd.music.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes5.dex */
public class ReflectionBitmapUtil {
    static final int reflectionGap = 4;

    public static Bitmap handleReflectBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, 100.0f, 553648127, 16777215, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, 0.0f, width, bitmapCreateBitmap.getHeight(), paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap getAlphaImage(Bitmap bitmap, int i) {
        int width = bitmap.getWidth() * bitmap.getHeight();
        int[] iArr = new int[width];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        double d = (i * 255) / 100;
        double d2 = d / ((double) width);
        for (int i2 = 0; i2 < width; i2++) {
            iArr[i2] = (((int) (d - (((double) i2) * d2))) << 24) | (iArr[i2] & 16777215);
        }
        return Bitmap.createBitmap(iArr, bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    private static Bitmap getB1(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        int i = height / 2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, i, width, i, matrix, false);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width, i + height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        float f = height;
        float f2 = width;
        float f3 = height + 4;
        canvas.drawRect(0.0f, f, f2, f3, new Paint());
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, f3, (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, bitmap.getHeight(), 0.0f, bitmapCreateBitmap2.getHeight() + 4, 822083583, 16777215, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, f, f2, bitmapCreateBitmap2.getHeight() + 4, paint);
        return bitmapCreateBitmap2;
    }
}
