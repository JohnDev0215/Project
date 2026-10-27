package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public final class w implements com.bumptech.glide.load.k<Bitmap, Bitmap> {

    private static final class a implements com.bumptech.glide.load.n.v<Bitmap> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Bitmap f2372b;

        a(Bitmap bitmap) {
            this.f2372b = bitmap;
        }

        @Override // com.bumptech.glide.load.n.v
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap b() {
            return this.f2372b;
        }

        @Override // com.bumptech.glide.load.n.v
        public int c() {
            return com.bumptech.glide.t.k.g(this.f2372b);
        }

        @Override // com.bumptech.glide.load.n.v
        public Class<Bitmap> d() {
            return Bitmap.class;
        }

        @Override // com.bumptech.glide.load.n.v
        public void f() {
        }
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(Bitmap bitmap, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new a(bitmap);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Bitmap bitmap, com.bumptech.glide.load.i iVar) {
        return true;
    }
}
