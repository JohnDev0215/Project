package com.amazon.music.picassoimageloader;

import android.graphics.Bitmap;
import com.squareup.picasso.Transformation;

/* JADX INFO: loaded from: classes4.dex */
public class ImageBlurTransformation implements Transformation {
    private int blurRadius;

    public ImageBlurTransformation() {
        this.blurRadius = 30;
    }

    public ImageBlurTransformation(int i) {
        this.blurRadius = i;
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - iMin) / 2, (bitmap.getHeight() - iMin) / 2, iMin, iMin);
        Bitmap bitmapGenerateBlurredBitmap = ImageBlur.generateBlurredBitmap(bitmapCreateBitmap, this.blurRadius);
        bitmapCreateBitmap.recycle();
        bitmap.recycle();
        return bitmapGenerateBlurredBitmap;
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return ImageBlurTransformation.class.getSimpleName();
    }
}
