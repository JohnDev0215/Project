package com.facebook.common.memory;

import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public interface PooledByteBuffer extends Closeable {
    boolean isClosed();

    byte read(int offset);

    int read(int offset, byte[] buffer, int bufferOffset, int length);

    int size();

    public static class ClosedException extends RuntimeException {
        public ClosedException() {
            super("Invalid bytebuf. Already closed");
        }
    }
}
