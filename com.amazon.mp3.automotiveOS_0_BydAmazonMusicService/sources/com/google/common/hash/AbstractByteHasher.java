package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractByteHasher extends AbstractHasher {
    private final ByteBuffer scratch = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    protected abstract void update(byte b);

    AbstractByteHasher() {
    }

    protected void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    protected void update(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            update(bArr[i3]);
        }
    }

    @Override // com.google.common.hash.Hasher
    public Hasher putByte(byte b) {
        update(b);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher
    public Hasher putBytes(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        update(bArr);
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher
    public Hasher putBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        update(bArr, i, i2);
        return this;
    }
}
