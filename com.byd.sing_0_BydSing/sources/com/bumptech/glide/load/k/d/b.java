package com.bumptech.glide.load.k.d;

import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.m.j;

/* JADX INFO: compiled from: BytesResource.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements s<byte[]> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f3061b;

    public b(byte[] bArr) {
        this.f3061b = (byte[]) j.d(bArr);
    }

    @Override // com.bumptech.glide.load.engine.s
    public void a() {
    }

    @Override // com.bumptech.glide.load.engine.s
    public int b() {
        return this.f3061b.length;
    }

    @Override // com.bumptech.glide.load.engine.s
    public Class<byte[]> c() {
        return byte[].class;
    }

    @Override // com.bumptech.glide.load.engine.s
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public byte[] get() {
        return this.f3061b;
    }
}
