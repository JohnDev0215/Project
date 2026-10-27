package com.bumptech.glide.load.p.i;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.n.v;

/* JADX INFO: loaded from: classes.dex */
public final class c implements e<Drawable, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.e f2412a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e<Bitmap, byte[]> f2413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e<com.bumptech.glide.load.p.h.c, byte[]> f2414c;

    public c(com.bumptech.glide.load.n.a0.e eVar, e<Bitmap, byte[]> eVar2, e<com.bumptech.glide.load.p.h.c, byte[]> eVar3) {
        this.f2412a = eVar;
        this.f2413b = eVar2;
        this.f2414c = eVar3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static v<com.bumptech.glide.load.p.h.c> b(v<Drawable> vVar) {
        return vVar;
    }

    @Override // com.bumptech.glide.load.p.i.e
    public v<byte[]> a(v<Drawable> vVar, i iVar) {
        Drawable drawableB = vVar.b();
        if (drawableB instanceof BitmapDrawable) {
            return this.f2413b.a(com.bumptech.glide.load.p.d.e.g(((BitmapDrawable) drawableB).getBitmap(), this.f2412a), iVar);
        }
        if (drawableB instanceof com.bumptech.glide.load.p.h.c) {
            return this.f2414c.a(b(vVar), iVar);
        }
        return null;
    }
}
