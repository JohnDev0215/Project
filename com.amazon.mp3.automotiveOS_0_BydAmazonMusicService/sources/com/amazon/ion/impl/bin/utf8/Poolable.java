package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Poolable;
import java.io.Closeable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Poolable<T extends Poolable<T>> implements Closeable {
    private final Pool<T> pool;

    Poolable(Pool<T> pool) {
        this.pool = pool;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.pool.returnToPool(this);
    }
}
