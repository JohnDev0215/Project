package com.bumptech.glide.load.j.e;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.s;

/* JADX INFO: compiled from: NonOwnedDrawableResource.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends b<Drawable> {
    private c(Drawable drawable) {
        super(drawable);
    }

    static s<Drawable> f(Drawable drawable) {
        if (drawable != null) {
            return new c(drawable);
        }
        return null;
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return Math.max(1, this.f3292b.getIntrinsicWidth() * this.f3292b.getIntrinsicHeight() * 4);
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<Drawable> d() {
        return this.f3292b.getClass();
    }
}
