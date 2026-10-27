package com.bumptech.glide.load.k.g;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.s;

/* JADX INFO: compiled from: GifFrameResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements com.bumptech.glide.load.f<com.bumptech.glide.i.a, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3082a;

    public h(com.bumptech.glide.load.engine.x.e eVar) {
        this.f3082a = eVar;
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public s<Bitmap> a(com.bumptech.glide.i.a aVar, int i, int i2, com.bumptech.glide.load.e eVar) {
        return com.bumptech.glide.load.resource.bitmap.e.e(aVar.b(), this.f3082a);
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(com.bumptech.glide.i.a aVar, com.bumptech.glide.load.e eVar) {
        return true;
    }
}
