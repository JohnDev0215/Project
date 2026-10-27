package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlatformBitmapFactory {
    public abstract CloseableReference<Bitmap> createBitmapInternal(int width, int height, Bitmap.Config bitmapConfig);

    public CloseableReference<Bitmap> createBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        return createBitmap(width, height, bitmapConfig, null);
    }

    public CloseableReference<Bitmap> createBitmap(int width, int height) {
        return createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    public CloseableReference<Bitmap> createBitmap(int width, int height, Bitmap.Config bitmapConfig, @Nullable Object callerContext) {
        return createBitmapInternal(width, height, bitmapConfig);
    }
}
