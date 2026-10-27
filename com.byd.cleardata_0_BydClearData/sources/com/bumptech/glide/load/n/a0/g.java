package com.bumptech.glide.load.n.a0;

/* JADX INFO: loaded from: classes.dex */
public final class g implements a<byte[]> {
    @Override // com.bumptech.glide.load.n.a0.a
    public String a() {
        return "ByteArrayPool";
    }

    @Override // com.bumptech.glide.load.n.a0.a
    public int c() {
        return 1;
    }

    @Override // com.bumptech.glide.load.n.a0.a
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public int b(byte[] bArr) {
        return bArr.length;
    }

    @Override // com.bumptech.glide.load.n.a0.a
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public byte[] newArray(int i) {
        return new byte[i];
    }
}
