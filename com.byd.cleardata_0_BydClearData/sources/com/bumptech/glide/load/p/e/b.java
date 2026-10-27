package com.bumptech.glide.load.p.e;

import com.bumptech.glide.load.n.v;
import com.bumptech.glide.t.j;

/* JADX INFO: loaded from: classes.dex */
public class b implements v<byte[]> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final byte[] f2382b;

    public b(byte[] bArr) {
        this.f2382b = (byte[]) j.d(bArr);
    }

    @Override // com.bumptech.glide.load.n.v
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public byte[] b() {
        return this.f2382b;
    }

    @Override // com.bumptech.glide.load.n.v
    public int c() {
        return this.f2382b.length;
    }

    @Override // com.bumptech.glide.load.n.v
    public Class<byte[]> d() {
        return byte[].class;
    }

    @Override // com.bumptech.glide.load.n.v
    public void f() {
    }
}
