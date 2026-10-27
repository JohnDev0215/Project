package com.bumptech.glide.load.n.a0;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class f implements e {
    @Override // com.bumptech.glide.load.n.a0.e
    public void a(int i) {
    }

    @Override // com.bumptech.glide.load.n.a0.e
    public void b() {
    }

    @Override // com.bumptech.glide.load.n.a0.e
    public Bitmap c(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.bumptech.glide.load.n.a0.e
    public void d(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // com.bumptech.glide.load.n.a0.e
    public Bitmap e(int i, int i2, Bitmap.Config config) {
        return c(i, i2, config);
    }
}
