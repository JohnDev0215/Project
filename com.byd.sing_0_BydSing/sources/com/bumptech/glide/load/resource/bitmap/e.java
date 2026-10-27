package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapResource.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements com.bumptech.glide.load.engine.s<Bitmap>, com.bumptech.glide.load.engine.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Bitmap f3120b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3121c;

    public e(Bitmap bitmap, com.bumptech.glide.load.engine.x.e eVar) {
        this.f3120b = (Bitmap) com.bumptech.glide.m.j.e(bitmap, "Bitmap must not be null");
        this.f3121c = (com.bumptech.glide.load.engine.x.e) com.bumptech.glide.m.j.e(eVar, "BitmapPool must not be null");
    }

    public static e e(Bitmap bitmap, com.bumptech.glide.load.engine.x.e eVar) {
        if (bitmap == null) {
            return null;
        }
        return new e(bitmap, eVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
        this.f3121c.d(this.f3120b);
    }

    @Override // com.bumptech.glide.load.engine.s
    public int b() {
        return com.bumptech.glide.m.k.g(this.f3120b);
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Bitmap> c() {
        return Bitmap.class;
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Bitmap get() {
        return this.f3120b;
    }

    @Override // com.bumptech.glide.load.engine.o
    public void initialize() {
        this.f3120b.prepareToDraw();
    }
}
