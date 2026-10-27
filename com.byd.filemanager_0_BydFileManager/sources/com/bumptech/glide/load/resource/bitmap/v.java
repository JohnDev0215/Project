package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: UnitBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class v implements com.bumptech.glide.load.f<Bitmap, Bitmap> {

    /* JADX INFO: compiled from: UnitBitmapDecoder.java */
    private static final class a implements com.bumptech.glide.load.engine.s<Bitmap> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Bitmap f3394b;

        a(Bitmap bitmap) {
            this.f3394b = bitmap;
        }

        @Override // com.bumptech.glide.load.engine.s
        public void a() {
        }

        @Override // com.bumptech.glide.load.engine.s
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Bitmap get() {
            return this.f3394b;
        }

        @Override // com.bumptech.glide.load.engine.s
        public int c() {
            return com.bumptech.glide.o.k.g(this.f3394b);
        }

        @Override // com.bumptech.glide.load.engine.s
        public Class<Bitmap> d() {
            return Bitmap.class;
        }
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.engine.s<Bitmap> a(Bitmap bitmap, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new a(bitmap);
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Bitmap bitmap, com.bumptech.glide.load.e eVar) {
        return true;
    }
}
