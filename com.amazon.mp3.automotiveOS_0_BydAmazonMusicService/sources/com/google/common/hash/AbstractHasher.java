package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractHasher implements Hasher {
    AbstractHasher() {
    }

    @Override // com.google.common.hash.Hasher
    public Hasher putString(CharSequence charSequence, Charset charset) {
        return putBytes(charSequence.toString().getBytes(charset));
    }

    public Hasher putBytes(byte[] bArr) {
        return putBytes(bArr, 0, bArr.length);
    }

    public Hasher putBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            putByte(bArr[i + i3]);
        }
        return this;
    }
}
