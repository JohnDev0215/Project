package com.google.common.hash;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
abstract class AbstractHasher implements Hasher {
    @Override // com.google.common.hash.Hasher
    public abstract Hasher putBytes(byte[] bArr, int i, int i2);

    AbstractHasher() {
    }

    @Override // com.google.common.hash.Hasher
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    public Hasher putBytes(byte[] bArr) {
        return putBytes(bArr, 0, bArr.length);
    }
}
