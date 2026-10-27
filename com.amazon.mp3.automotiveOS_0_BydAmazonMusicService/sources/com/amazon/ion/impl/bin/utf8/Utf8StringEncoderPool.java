package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Pool;

/* JADX INFO: loaded from: classes3.dex */
public class Utf8StringEncoderPool extends Pool<Utf8StringEncoder> {
    private static final Utf8StringEncoderPool INSTANCE = new Utf8StringEncoderPool();

    @Override // com.amazon.ion.impl.bin.utf8.Pool
    public /* bridge */ /* synthetic */ Poolable getOrCreate() {
        return super.getOrCreate();
    }

    private Utf8StringEncoderPool() {
        super(new Pool.Allocator<Utf8StringEncoder>() { // from class: com.amazon.ion.impl.bin.utf8.Utf8StringEncoderPool.1
            @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
            public /* bridge */ /* synthetic */ Poolable newInstance(Pool pool) {
                return newInstance((Pool<Utf8StringEncoder>) pool);
            }

            @Override // com.amazon.ion.impl.bin.utf8.Pool.Allocator
            public Utf8StringEncoder newInstance(Pool<Utf8StringEncoder> pool) {
                return new Utf8StringEncoder(pool);
            }
        });
    }

    public static Utf8StringEncoderPool getInstance() {
        return INSTANCE;
    }
}
