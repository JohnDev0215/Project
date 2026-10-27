package com.bumptech.glide.load.engine.x;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapPoolAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements e {
    @Override // com.bumptech.glide.load.engine.x.e
    public void a(int i) {
    }

    @Override // com.bumptech.glide.load.engine.x.e
    public void b() {
    }

    @Override // com.bumptech.glide.load.engine.x.e
    public Bitmap c(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.bumptech.glide.load.engine.x.e
    public void d(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // com.bumptech.glide.load.engine.x.e
    public Bitmap e(int i, int i2, Bitmap.Config config) {
        return c(i, i2, config);
    }
}
