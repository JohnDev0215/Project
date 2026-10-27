package com.byd.music.utils;

import android.graphics.Bitmap;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes5.dex */
public class ReflectTransformation extends BitmapTransformation {
    private static final String TAG = "ReflectTransformation";
    private static final String TRANSFORMATIONS_ID = "com.bumptech.glide.transformations.ReflectTransformation";
    private static final byte[] TRANSFORMATIONS_ID_BYTES = TRANSFORMATIONS_ID.getBytes(Charset.forName(Key.STRING_CHARSET_NAME));

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return 573380420;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        LogUtil.d(TAG, "prepare transform picture");
        return ReflectionBitmapUtil.handleReflectBitmap(bitmap);
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(TRANSFORMATIONS_ID_BYTES);
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        return obj instanceof ReflectTransformation;
    }
}
