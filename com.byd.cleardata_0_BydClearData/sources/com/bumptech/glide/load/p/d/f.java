package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class f implements com.bumptech.glide.load.k<ByteBuffer, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f2324a;

    public f(j jVar) {
        this.f2324a = jVar;
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.load.i iVar) {
        return this.f2324a.f(com.bumptech.glide.t.a.f(byteBuffer), i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, com.bumptech.glide.load.i iVar) {
        return this.f2324a.q(byteBuffer);
    }
}
