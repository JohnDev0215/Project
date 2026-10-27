package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferBitmapImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements com.bumptech.glide.load.f<ByteBuffer, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f3123a = new d();

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.engine.s<Bitmap> a(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        return this.f3123a.a(ImageDecoder.createSource(byteBuffer), i, i2, eVar);
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, com.bumptech.glide.load.e eVar) throws IOException {
        return true;
    }
}
