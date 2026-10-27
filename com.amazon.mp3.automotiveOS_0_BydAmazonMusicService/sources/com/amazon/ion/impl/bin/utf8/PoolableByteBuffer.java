package com.amazon.ion.impl.bin.utf8;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class PoolableByteBuffer extends Poolable<PoolableByteBuffer> {
    private final ByteBuffer buffer;

    @Override // com.amazon.ion.impl.bin.utf8.Poolable, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    PoolableByteBuffer(Pool<PoolableByteBuffer> pool) {
        super(pool);
        this.buffer = ByteBuffer.allocate(4096);
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }
}
