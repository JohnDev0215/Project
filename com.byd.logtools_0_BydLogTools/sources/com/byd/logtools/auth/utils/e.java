package com.byd.logtools.auth.utils;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: QrCodeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static Bitmap a(String str, int i) {
        try {
            return b(com.king.zxing.t.a.b(str, i));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Bitmap b(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width;
        int i2 = height;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < height; i5++) {
            for (int i6 = 0; i6 < width; i6++) {
                if (bitmap.getPixel(i6, i5) != -1) {
                    if (i6 < i) {
                        i = i6;
                    }
                    if (i6 > i3) {
                        i3 = i6;
                    }
                    if (i5 < i2) {
                        i2 = i5;
                    }
                    if (i5 > i4) {
                        i4 = i5;
                    }
                }
            }
        }
        return (i3 < i || i4 < i2) ? bitmap : Bitmap.createBitmap(bitmap, i, i2, (i3 - i) + 1, (i4 - i2) + 1);
    }
}
