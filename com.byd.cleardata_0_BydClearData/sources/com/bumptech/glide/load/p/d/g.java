package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class g implements com.bumptech.glide.load.k<ByteBuffer, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f2325a = new d();

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.load.i iVar) {
        return this.f2325a.a(ImageDecoder.createSource(byteBuffer), i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, com.bumptech.glide.load.i iVar) {
        return true;
    }
}
