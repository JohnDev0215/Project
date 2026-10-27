package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface PlatformDecoder {
    CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(final EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, @Nullable final ColorSpace colorSpace);

    CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config bitmapConfig, @Nullable Rect regionToDecode, int length, @Nullable final ColorSpace colorSpace);
}
