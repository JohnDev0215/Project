package com.amazon.ion.impl.bin;

import java.io.Closeable;

/* JADX INFO: loaded from: classes3.dex */
abstract class BlockAllocator implements Closeable {
    public abstract Block allocateBlock();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract int getBlockSize();

    BlockAllocator() {
    }
}
