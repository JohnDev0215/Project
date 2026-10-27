package com.bumptech.glide.load.p.f;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.n.v;

/* JADX INFO: loaded from: classes.dex */
final class c extends b<Drawable> {
    private c(Drawable drawable) {
        super(drawable);
    }

    static v<Drawable> g(Drawable drawable) {
        if (drawable != null) {
            return new c(drawable);
        }
        return null;
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return Math.max(1, this.f2384b.getIntrinsicWidth() * this.f2384b.getIntrinsicHeight() * 4);
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<Drawable> d() {
        return this.f2384b.getClass();
    }

    @Override // com.bumptech.glide.load.n.v
    public void f() {
    }
}
