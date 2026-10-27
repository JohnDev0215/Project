package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class BitmapPoolBackend extends LruBucketsPoolBackend<Bitmap> {
    @Override // com.facebook.imagepipeline.memory.LruBucketsPoolBackend, com.facebook.imagepipeline.memory.PoolBackend
    public void put(Bitmap bitmap) {
        if (isReusable(bitmap)) {
            super.put(bitmap);
        }
    }

    @Override // com.facebook.imagepipeline.memory.LruBucketsPoolBackend, com.facebook.imagepipeline.memory.PoolBackend
    @Nullable
    public Bitmap get(int size) {
        Bitmap bitmap = (Bitmap) super.get(size);
        if (bitmap == null || !isReusable(bitmap)) {
            return null;
        }
        bitmap.eraseColor(0);
        return bitmap;
    }

    @Override // com.facebook.imagepipeline.memory.PoolBackend
    public int getSize(Bitmap bitmap) {
        return BitmapUtil.getSizeInBytes(bitmap);
    }

    protected boolean isReusable(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        if (bitmap.isRecycled()) {
            FLog.wtf("BitmapPoolBackend", "Cannot reuse a recycled bitmap: %s", bitmap);
            return false;
        }
        if (bitmap.isMutable()) {
            return true;
        }
        FLog.wtf("BitmapPoolBackend", "Cannot reuse an immutable bitmap: %s", bitmap);
        return false;
    }
}
