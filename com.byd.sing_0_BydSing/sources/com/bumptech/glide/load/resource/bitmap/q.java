package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/* JADX INFO: compiled from: ResourceBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class q implements com.bumptech.glide.load.f<Uri, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.k.e.d f3145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3146b;

    public q(com.bumptech.glide.load.k.e.d dVar, com.bumptech.glide.load.engine.x.e eVar) {
        this.f3145a = dVar;
        this.f3146b = eVar;
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.engine.s<Bitmap> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        com.bumptech.glide.load.engine.s<Drawable> sVarA = this.f3145a.a(uri, i, i2, eVar);
        if (sVarA == null) {
            return null;
        }
        return i.a(this.f3146b, sVarA.get(), i, i2);
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri, com.bumptech.glide.load.e eVar) {
        return "android.resource".equals(uri.getScheme());
    }
}
