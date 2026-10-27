package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapResource.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements com.bumptech.glide.load.engine.s<Bitmap>, com.bumptech.glide.load.engine.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Bitmap f3355b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3356c;

    public e(Bitmap bitmap, com.bumptech.glide.load.engine.x.e eVar) {
        this.f3355b = (Bitmap) com.bumptech.glide.o.j.e(bitmap, "Bitmap must not be null");
        this.f3356c = (com.bumptech.glide.load.engine.x.e) com.bumptech.glide.o.j.e(eVar, "BitmapPool must not be null");
    }

    public static e f(Bitmap bitmap, com.bumptech.glide.load.engine.x.e eVar) {
        if (bitmap == null) {
            return null;
        }
        return new e(bitmap, eVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
        this.f3356c.d(this.f3355b);
    }

    @Override // com.bumptech.glide.load.engine.o
    public void b() {
        this.f3355b.prepareToDraw();
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return com.bumptech.glide.o.k.g(this.f3355b);
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Bitmap> d() {
        return Bitmap.class;
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Bitmap get() {
        return this.f3355b;
    }
}
