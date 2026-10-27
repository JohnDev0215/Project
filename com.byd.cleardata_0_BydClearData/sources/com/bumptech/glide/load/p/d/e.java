package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes.dex */
public class e implements com.bumptech.glide.load.n.v<Bitmap>, com.bumptech.glide.load.n.r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Bitmap f2322b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.e f2323c;

    public e(Bitmap bitmap, com.bumptech.glide.load.n.a0.e eVar) {
        this.f2322b = (Bitmap) com.bumptech.glide.t.j.e(bitmap, "Bitmap must not be null");
        this.f2323c = (com.bumptech.glide.load.n.a0.e) com.bumptech.glide.t.j.e(eVar, "BitmapPool must not be null");
    }

    public static e g(Bitmap bitmap, com.bumptech.glide.load.n.a0.e eVar) {
        if (bitmap == null) {
            return null;
        }
        return new e(bitmap, eVar);
    }

    @Override // com.bumptech.glide.load.n.r
    public void a() {
        this.f2322b.prepareToDraw();
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return com.bumptech.glide.t.k.g(this.f2322b);
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<Bitmap> d() {
        return Bitmap.class;
    }

    @Override // com.bumptech.glide.load.n.v
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Bitmap b() {
        return this.f2322b;
    }

    @Override // com.bumptech.glide.load.n.v
    public void f() {
        this.f2323c.d(this.f2322b);
    }
}
