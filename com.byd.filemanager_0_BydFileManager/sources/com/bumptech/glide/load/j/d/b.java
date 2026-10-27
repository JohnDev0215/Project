package com.bumptech.glide.load.j.d;

import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.o.j;

/* JADX INFO: compiled from: BytesResource.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements s<byte[]> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f3290b;

    public b(byte[] bArr) {
        this.f3290b = (byte[]) j.d(bArr);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public byte[] get() {
        return this.f3290b;
    }

    @Override // com.bumptech.glide.load.engine.s
    public int c() {
        return this.f3290b.length;
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<byte[]> d() {
        return byte[].class;
    }
}
