package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* JADX INFO: compiled from: LazyBitmapDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
public final class o implements com.bumptech.glide.load.engine.s<BitmapDrawable>, com.bumptech.glide.load.engine.o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f3142b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.s<Bitmap> f3143c;

    private o(Resources resources, com.bumptech.glide.load.engine.s<Bitmap> sVar) {
        this.f3142b = (Resources) com.bumptech.glide.m.j.d(resources);
        this.f3143c = (com.bumptech.glide.load.engine.s) com.bumptech.glide.m.j.d(sVar);
    }

    public static com.bumptech.glide.load.engine.s<BitmapDrawable> e(Resources resources, com.bumptech.glide.load.engine.s<Bitmap> sVar) {
        if (sVar == null) {
            return null;
        }
        return new o(resources, sVar);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
        this.f3143c.a();
    }

    @Override // com.bumptech.glide.load.engine.s
    public int b() {
        return this.f3143c.b();
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<BitmapDrawable> c() {
        return BitmapDrawable.class;
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public BitmapDrawable get() {
        return new BitmapDrawable(this.f3142b, this.f3143c.get());
    }

    @Override // com.bumptech.glide.load.engine.o
    public void initialize() {
        com.bumptech.glide.load.engine.s<Bitmap> sVar = this.f3143c;
        if (sVar instanceof com.bumptech.glide.load.engine.o) {
            ((com.bumptech.glide.load.engine.o) sVar).initialize();
        }
    }
}
