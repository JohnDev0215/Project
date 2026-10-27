package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public class DummyBitmapPool implements BitmapPool {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.facebook.common.memory.Pool
    public Bitmap get(int size) {
        return Bitmap.createBitmap(1, (int) Math.ceil(((double) size) / 2.0d), Bitmap.Config.RGB_565);
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(Bitmap value) {
        Preconditions.checkNotNull(value);
        value.recycle();
    }
}
