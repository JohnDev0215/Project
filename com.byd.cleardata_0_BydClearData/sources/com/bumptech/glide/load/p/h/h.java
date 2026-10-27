package com.bumptech.glide.load.p.h;

import android.graphics.Bitmap;
import com.bumptech.glide.load.k;
import com.bumptech.glide.load.n.v;

/* JADX INFO: loaded from: classes.dex */
public final class h implements k<com.bumptech.glide.n.a, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.e f2403a;

    public h(com.bumptech.glide.load.n.a0.e eVar) {
        this.f2403a = eVar;
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v<Bitmap> a(com.bumptech.glide.n.a aVar, int i, int i2, com.bumptech.glide.load.i iVar) {
        return com.bumptech.glide.load.p.d.e.g(aVar.d(), this.f2403a);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(com.bumptech.glide.n.a aVar, com.bumptech.glide.load.i iVar) {
        return true;
    }
}
