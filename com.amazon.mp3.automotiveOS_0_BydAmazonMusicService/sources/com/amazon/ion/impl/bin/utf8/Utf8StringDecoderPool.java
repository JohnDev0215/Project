package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Pool;

/* JADX INFO: loaded from: classes3.dex */
public class Utf8StringDecoderPool extends Pool<Utf8StringDecoder> {
    private static final Utf8StringDecoderPool INSTANCE = new Utf8StringDecoderPool();

    @Override // com.amazon.ion.impl.bin.utf8.Pool
    public /* bridge */ /* synthetic */ Poolable getOrCreate() {
        return super.getOrCreate();
    }

    private Utf8StringDecoderPool() {
        super(new Pool.Allocator<Utf8StringDecoder>() { // from class: com.amazon.ion.impl.bin.utf8.Utf8StringDecoderPool.1
            @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
            public /* bridge */ /* synthetic */ Poolable newInstance(Pool pool) {
                return newInstance((Pool<Utf8StringDecoder>) pool);
            }

            @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
            public Utf8StringDecoder newInstance(Pool<Utf8StringDecoder> pool) {
                return new Utf8StringDecoder(pool);
            }
        });
    }

    public static Utf8StringDecoderPool getInstance() {
        return INSTANCE;
    }
}
