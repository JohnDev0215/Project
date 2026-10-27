package com.bumptech.glide.load.k.h;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.s;

/* JADX INFO: compiled from: DrawableBytesTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements e<Drawable, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3091a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e<Bitmap, byte[]> f3092b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e<com.bumptech.glide.load.k.g.c, byte[]> f3093c;

    public c(com.bumptech.glide.load.engine.x.e eVar, e<Bitmap, byte[]> eVar2, e<com.bumptech.glide.load.k.g.c, byte[]> eVar3) {
        this.f3091a = eVar;
        this.f3092b = eVar2;
        this.f3093c = eVar3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static s<com.bumptech.glide.load.k.g.c> b(s<Drawable> sVar) {
        return sVar;
    }

    @Override // com.bumptech.glide.load.k.h.e
    public s<byte[]> a(s<Drawable> sVar, com.bumptech.glide.load.e eVar) {
        Drawable drawable = sVar.get();
        if (drawable instanceof BitmapDrawable) {
            return this.f3092b.a(com.bumptech.glide.load.resource.bitmap.e.e(((BitmapDrawable) drawable).getBitmap(), this.f3091a), eVar);
        }
        if (drawable instanceof com.bumptech.glide.load.k.g.c) {
            return this.f3093c.a(b(sVar), eVar);
        }
        return null;
    }
}
