package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements com.bumptech.glide.load.f<ByteBuffer, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f3357a;

    public g(j jVar) {
        this.f3357a = jVar;
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.engine.s<Bitmap> a(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        return this.f3357a.f(com.bumptech.glide.o.a.f(byteBuffer), i, i2, eVar);
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, com.bumptech.glide.load.e eVar) {
        return this.f3357a.q(byteBuffer);
    }
}
