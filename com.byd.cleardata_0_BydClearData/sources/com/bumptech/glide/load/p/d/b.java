package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class b implements com.bumptech.glide.load.l<BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.e f2316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.l<Bitmap> f2317b;

    public b(com.bumptech.glide.load.n.a0.e eVar, com.bumptech.glide.load.l<Bitmap> lVar) {
        this.f2316a = eVar;
        this.f2317b = lVar;
    }

    @Override // com.bumptech.glide.load.l
    public com.bumptech.glide.load.c b(com.bumptech.glide.load.i iVar) {
        return this.f2317b.b(iVar);
    }

    @Override // com.bumptech.glide.load.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(com.bumptech.glide.load.n.v<BitmapDrawable> vVar, File file, com.bumptech.glide.load.i iVar) {
        return this.f2317b.a((Bitmap) new e(vVar.b().getBitmap(), this.f2316a), file, iVar);
    }
}
