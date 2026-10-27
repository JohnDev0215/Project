package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class p implements com.bumptech.glide.load.k<InputStream, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f2355a = new d();

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(InputStream inputStream, int i, int i2, com.bumptech.glide.load.i iVar) {
        return this.f2355a.a(ImageDecoder.createSource(com.bumptech.glide.t.a.b(inputStream)), i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(InputStream inputStream, com.bumptech.glide.load.i iVar) {
        return true;
    }
}
