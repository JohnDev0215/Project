package com.android.launcher3.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* JADX INFO: loaded from: classes4.dex */
public final class ShaderBitmapCache {
    private static ShaderBitmapCache INSTANCE = new ShaderBitmapCache();
    private Bitmap bitmap;
    private Canvas mCanvas;

    public static ShaderBitmapCache getInstance() {
        return INSTANCE;
    }

    public void initCache(int width, int height) {
        this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.mCanvas = new Canvas(this.bitmap);
    }

    public void clear() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.bitmap.recycle();
        }
        this.bitmap = null;
        this.mCanvas = null;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Canvas getCanvas() {
        return this.mCanvas;
    }
}
