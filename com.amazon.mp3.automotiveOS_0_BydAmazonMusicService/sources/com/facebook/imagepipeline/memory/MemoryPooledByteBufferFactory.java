package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class MemoryPooledByteBufferFactory implements PooledByteBufferFactory {
    private final MemoryChunkPool mPool;
    private final PooledByteStreams mPooledByteStreams;

    public MemoryPooledByteBufferFactory(MemoryChunkPool pool, PooledByteStreams pooledByteStreams) {
        this.mPool = pool;
        this.mPooledByteStreams = pooledByteStreams;
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream) throws Throwable {
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.mPool);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(byte[] bytes) throws Throwable {
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.mPool, bytes.length);
        try {
            try {
                memoryPooledByteBufferOutputStream.write(bytes, 0, bytes.length);
                return memoryPooledByteBufferOutputStream.toByteBuffer();
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBuffer newByteBuffer(InputStream inputStream, int initialCapacity) throws Throwable {
        MemoryPooledByteBufferOutputStream memoryPooledByteBufferOutputStream = new MemoryPooledByteBufferOutputStream(this.mPool, initialCapacity);
        try {
            return newByteBuf(inputStream, memoryPooledByteBufferOutputStream);
        } finally {
            memoryPooledByteBufferOutputStream.close();
        }
    }

    @VisibleForTesting
    MemoryPooledByteBuffer newByteBuf(InputStream inputStream, MemoryPooledByteBufferOutputStream outputStream) throws IOException {
        this.mPooledByteStreams.copy(inputStream, outputStream);
        return outputStream.toByteBuffer();
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBufferOutputStream newOutputStream() {
        return new MemoryPooledByteBufferOutputStream(this.mPool);
    }

    @Override // com.facebook.common.memory.PooledByteBufferFactory
    public MemoryPooledByteBufferOutputStream newOutputStream(int initialCapacity) {
        return new MemoryPooledByteBufferOutputStream(this.mPool, initialCapacity);
    }
}
