package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CloseableBitmap extends CloseableImage {
    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public abstract /* synthetic */ int getHeight();

    public abstract Bitmap getUnderlyingBitmap();

    @Override // com.facebook.imagepipeline.image.CloseableImage, com.facebook.imagepipeline.image.ImageInfo
    public abstract /* synthetic */ int getWidth();
}
