package com.amazon.ion.impl.bin;

import java.io.Closeable;

/* JADX INFO: loaded from: classes3.dex */
abstract class Block implements Closeable {
    public final byte[] data;
    public int limit = 0;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    Block(byte[] bArr) {
        this.data = bArr;
    }

    public final void reset() {
        this.limit = 0;
    }

    public final int remaining() {
        return this.data.length - this.limit;
    }
}
