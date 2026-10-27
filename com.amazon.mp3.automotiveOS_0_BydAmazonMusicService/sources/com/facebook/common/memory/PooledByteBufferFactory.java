package com.facebook.common.memory;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes5.dex */
public interface PooledByteBufferFactory {
    PooledByteBuffer newByteBuffer(InputStream inputStream) throws IOException;

    PooledByteBuffer newByteBuffer(InputStream inputStream, int initialCapacity) throws IOException;

    PooledByteBuffer newByteBuffer(byte[] bytes);

    PooledByteBufferOutputStream newOutputStream();

    PooledByteBufferOutputStream newOutputStream(int initialCapacity);
}
