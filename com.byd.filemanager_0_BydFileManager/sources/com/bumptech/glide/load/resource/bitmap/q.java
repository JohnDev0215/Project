package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: compiled from: LazyBitmapDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements com.bumptech.glide.load.engine.s<BitmapDrawable>, com.bumptech.glide.load.engine.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f3380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.s<Bitmap> f3381c;

    private q(Resources resources, com.bumptech.glide.load.engine.s<Bitmap> sVar) {
        this.f3380b = (Resources) com.bumptech.glide.o.j.d(resources);
        this.f3381c = (com.bumptech.glide.load.engine.s) com.bumptech.glide.o.j.d(sVar);
    }

    public static com.bumptech.glide.load.engine.s<BitmapDrawable> f(Resources resources, com.bumptech.glide.load.engine.s<Bitmap> sVar) {
        if (sVar == null) {
            return null;
        }
        return new q(resources, sVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
        this.f3381c.a();
    }

    @Override // com.bumptech.glide.load.engine.o
    public void b() {
        com.bumptech.glide.load.engine.s<Bitmap> sVar = this.f3381c;
        if (sVar instanceof com.bumptech.glide.load.engine.o) {
            ((com.bumptech.glide.load.engine.o) sVar).b();
        }
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return this.f3381c.c();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<BitmapDrawable> d() {
        return BitmapDrawable.class;
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public BitmapDrawable get() {
        return new BitmapDrawable(this.f3380b, this.f3381c.get());
    }
}
