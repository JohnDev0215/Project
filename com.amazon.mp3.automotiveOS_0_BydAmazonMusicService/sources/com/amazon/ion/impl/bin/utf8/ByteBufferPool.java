package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Pool;

/* JADX INFO: loaded from: classes3.dex */
public class ByteBufferPool extends Pool<PoolableByteBuffer> {
    private static final ByteBufferPool INSTANCE = new ByteBufferPool();

    @Override // com.amazon.ion.impl.bin.utf8.Pool
    public /* bridge */ /* synthetic */ Poolable getOrCreate() {
        return super.getOrCreate();
    }

    private ByteBufferPool() {
        super(new Pool.Allocator<PoolableByteBuffer>() { // from class: com.amazon.ion.impl.bin.utf8.ByteBufferPool.1
            @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
            public /* bridge */ /* synthetic */ Poolable newInstance(Pool pool) {
                return newInstance((Pool<PoolableByteBuffer>) pool);
            }

            @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
            public PoolableByteBuffer newInstance(Pool<PoolableByteBuffer> pool) {
                return new PoolableByteBuffer(pool);
            }
        });
    }

    public static ByteBufferPool getInstance() {
        return INSTANCE;
    }
}
