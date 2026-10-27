package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public class t implements com.bumptech.glide.load.k<Uri, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.p.f.d f2362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.e f2363b;

    public t(com.bumptech.glide.load.p.f.d dVar, com.bumptech.glide.load.n.a0.e eVar) {
        this.f2362a = dVar;
        this.f2363b = eVar;
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(Uri uri, int i, int i2, com.bumptech.glide.load.i iVar) {
        com.bumptech.glide.load.n.v<Drawable> vVarA = this.f2362a.a(uri, i, i2, iVar);
        if (vVarA == null) {
            return null;
        }
        return k.a(this.f2363b, vVarA.b(), i, i2);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri, com.bumptech.glide.load.i iVar) {
        return "android.resource".equals(uri.getScheme());
    }
}
