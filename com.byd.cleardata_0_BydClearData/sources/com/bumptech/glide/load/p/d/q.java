package com.bumptech.glide.load.p.d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class q implements com.bumptech.glide.load.n.v<BitmapDrawable>, com.bumptech.glide.load.n.r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f2356b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.n.v<Bitmap> f2357c;

    private q(Resources resources, com.bumptech.glide.load.n.v<Bitmap> vVar) {
        this.f2356b = (Resources) com.bumptech.glide.t.j.d(resources);
        this.f2357c = (com.bumptech.glide.load.n.v) com.bumptech.glide.t.j.d(vVar);
    }

    public static com.bumptech.glide.load.n.v<BitmapDrawable> g(Resources resources, com.bumptech.glide.load.n.v<Bitmap> vVar) {
        if (vVar == null) {
            return null;
        }
        return new q(resources, vVar);
    }

    @Override // com.bumptech.glide.load.n.r
    public void a() {
        com.bumptech.glide.load.n.v<Bitmap> vVar = this.f2357c;
        if (vVar instanceof com.bumptech.glide.load.n.r) {
            ((com.bumptech.glide.load.n.r) vVar).a();
        }
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return this.f2357c.c();
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<BitmapDrawable> d() {
        return BitmapDrawable.class;
    }

    @Override // com.bumptech.glide.load.n.v
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public BitmapDrawable b() {
        return new BitmapDrawable(this.f2356b, this.f2357c.b());
    }

    @Override // com.bumptech.glide.load.n.v
    public void f() {
        this.f2357c.f();
    }
}
