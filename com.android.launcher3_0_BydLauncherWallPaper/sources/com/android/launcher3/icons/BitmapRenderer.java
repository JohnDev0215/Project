package com.android.launcher3.icons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public interface BitmapRenderer {
    public static final boolean USE_HARDWARE_BITMAP;

    void draw(Canvas out);

    static {
        USE_HARDWARE_BITMAP = Build.VERSION.SDK_INT >= 28;
    }

    static Bitmap createSoftwareBitmap(int width, int height, BitmapRenderer renderer) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        renderer.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    static Bitmap createHardwareBitmap(int width, int height, BitmapRenderer renderer) {
        if (!USE_HARDWARE_BITMAP) {
            return createSoftwareBitmap(width, height, renderer);
        }
        Picture picture = new Picture();
        renderer.draw(picture.beginRecording(width, height));
        picture.endRecording();
        return Bitmap.createBitmap(picture);
    }
}
